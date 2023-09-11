package com.wd.demo.flink.window;

import com.wd.demo.flink.source.WaterSensor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @Author: wangd
 * @Date: 2023/7/9 16:21
 */
public class WindowAggAndProcessDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        SingleOutputStreamOperator<WaterSensor> sensorDS = env.socketTextStream("localhost", 7777).map(s -> {
            String[] data = s.split(",");
            return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
        });

        KeyedStream<WaterSensor, String> sensorKS = sensorDS.keyBy(WaterSensor::getId);

        // 基于时间的
        WindowedStream<WaterSensor, String, TimeWindow> window = sensorKS.window(TumblingProcessingTimeWindows.of(Time.seconds(10)));

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

        env.execute();
    }
}
