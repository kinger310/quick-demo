package com.wd.demo.flink.watermark;

import com.wd.demo.flink.source.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.time.Duration;

/**
 * @Author: wangd
 * @Date: 2023/7/9 16:21
 */
public class WatermarkLatenessDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setParallelism(1);

        // 演示watermark多并行度下的传递
        // env.setParallelism(2);

        SingleOutputStreamOperator<WaterSensor> sensorDS = env.socketTextStream("localhost", 7777).map(s -> {
            String[] data = s.split(",");
            return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
        });

        WatermarkStrategy<WaterSensor> watermarkStrategy = WatermarkStrategy
                .<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                .withTimestampAssigner(new SerializableTimestampAssigner<WaterSensor>() {
                    @Override
                    public long extractTimestamp(WaterSensor element, long recordTimestamp) {
                        System.out.println("element = " + element + ", recordTS=" + recordTimestamp);
                        return element.getTs() * 1000;
                    }
                });

        SingleOutputStreamOperator<WaterSensor> sensorDSWithWM = sensorDS.assignTimestampsAndWatermarks(watermarkStrategy);

        KeyedStream<WaterSensor, String> sensorKS = sensorDSWithWM.keyBy(WaterSensor::getId);

        OutputTag<WaterSensor> lateTag = new OutputTag<>("lateTag", Types.POJO(WaterSensor.class));
        // 基于时间的
        WindowedStream<WaterSensor, String, TimeWindow> window =
                sensorKS.window(TumblingEventTimeWindows.of(Time.seconds(10)))
                        .allowedLateness(Time.seconds(3))
                        .sideOutputLateData(lateTag);

        AggregateFunction<WaterSensor, Integer, String> agg = new AggregateFunction<WaterSensor, Integer, String>() {
            @Override
            public Integer createAccumulator() {
                System.out.println("创建累加器，初始值");
                return 0;
            }

            @Override
            public Integer add(WaterSensor value, Integer accumulator) {
                System.out.println("调用add" + value);
                return value.getVc() + accumulator;
            }

            @Override
            public String getResult(Integer accumulator) {
                System.out.println("调用getResult");
                return accumulator.toString();
            }

            @Override
            public Integer merge(Integer a, Integer b) {
                System.out.println("调用merge。只有回话窗口用到");
                return null;
            }
        };


        ProcessWindowFunction<String, String, String, TimeWindow> windowFunction = new ProcessWindowFunction<String, String, String, TimeWindow>() {
            @Override
            public void process(String s, ProcessWindowFunction<String, String, String, TimeWindow>.Context context, Iterable<String> elements, Collector<String> out) throws Exception {
                long start = context.window().getStart();
                long end = context.window().getEnd();
                String windowStart = DateFormatUtils.format(start, "yyyy=MM-dd HH:mm:ss.SSS");
                String windowEnd = DateFormatUtils.format(end, "yyyy=MM-dd HH:mm:ss.SSS");

                long count = elements.spliterator().estimateSize();

                out.collect(String.format("key=%s, 窗口[%s, %s], count=%s, 数据---》%s", s, windowStart, windowEnd, count, elements.toString()));

            }
        };
        SingleOutputStreamOperator<String> aggregate = window.aggregate(agg, windowFunction);

        aggregate.print();

        SideOutputDataStream<WaterSensor> sideOutput = aggregate.getSideOutput(lateTag);
        sideOutput.printToErr("late-data");

        env.execute();
    }
}
