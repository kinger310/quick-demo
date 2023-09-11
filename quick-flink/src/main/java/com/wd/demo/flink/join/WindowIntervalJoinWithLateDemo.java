package com.wd.demo.flink.join;

import com.wd.demo.flink.source.WaterSensor;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.time.Duration;

/**
 * @Author: wangd
 * @Date: 2023/7/10 10:05
 */
public class WindowIntervalJoinWithLateDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setParallelism(1);

        // SingleOutputStreamOperator<Tuple2<String, Integer>> ds1 = env.fromElements(
        //         Tuple2.of("a", 1),
        //         Tuple2.of("a", 2),
        //         Tuple2.of("b", 3),
        //         Tuple2.of("c", 4)
        //
        // ).assignTimestampsAndWatermarks(
        //         WatermarkStrategy
        //                 .<Tuple2<String, Integer>>forMonotonousTimestamps()
        //                 .withTimestampAssigner((element, recordTimestamp) -> element.f1 * 1000L));
        // SingleOutputStreamOperator<Tuple3<String, Integer, Integer>> ds2 = env.fromElements(
        //         Tuple3.of("a", 1, 1),
        //         Tuple3.of("a", 11, 1),
        //         Tuple3.of("b", 2, 1),
        //         Tuple3.of("b", 12, 1),
        //         Tuple3.of("c", 14, 1),
        //         Tuple3.of("d", 15, 1)
        // ).assignTimestampsAndWatermarks(
        //         WatermarkStrategy
        //                 .<Tuple3<String, Integer, Integer>>forMonotonousTimestamps()
        //                 .withTimestampAssigner((element, recordTimestamp) -> element.f1 * 1000L));

        SingleOutputStreamOperator<Tuple2<String, Integer>> ds1 =
                env.socketTextStream("localhost", 7777).map(s -> {
                    String[] data = s.split(",");
                    return Tuple2.of(data[0], Integer.valueOf(data[1]));
                })
                        .returns(Types.TUPLE(Types.STRING, Types.INT))
                        .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple2<String, Integer>>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                                .withTimestampAssigner((element, recordTimestamp) -> element.f1 * 1000L)
                );

        SingleOutputStreamOperator<Tuple3<String, Integer, Integer>> ds2 =
                env.socketTextStream("localhost", 8888).map(s -> {
                    String[] data = s.split(",");
                    return Tuple3.of(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2]));
                })
                        .returns(Types.TUPLE(Types.STRING, Types.INT, Types.INT))
                        .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple3<String, Integer, Integer>>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                                .withTimestampAssigner((element, recordTimestamp) -> element.f1 * 1000L)
                );



        KeyedStream<Tuple2<String, Integer>, String> ks1 = ds1.keyBy(k -> k.f0);
        KeyedStream<Tuple3<String, Integer, Integer>, String> ks2 = ds2.keyBy(k -> k.f0);


        OutputTag<Tuple2<String, Integer>> leftTag = new OutputTag<>("leftTag", Types.TUPLE(Types.STRING, Types.INT));
        OutputTag<Tuple3<String, Integer, Integer>> rightTag = new OutputTag<>("rightTag", Types.TUPLE(Types.STRING, Types.INT, Types.INT));

        SingleOutputStreamOperator<String> process = ks1.intervalJoin(ks2)
                .between(Time.seconds(-2), Time.seconds(2))
                .sideOutputLeftLateData(leftTag)
                .sideOutputRightLateData(rightTag)
                .process(new ProcessJoinFunction<Tuple2<String, Integer>, Tuple3<String, Integer, Integer>, String>() {
                    @Override
                    public void processElement(Tuple2<String, Integer> left, Tuple3<String, Integer, Integer> right, ProcessJoinFunction<Tuple2<String, Integer>, Tuple3<String, Integer, Integer>, String>.Context ctx, Collector<String> out) throws Exception {
                        out.collect(left + "<------>" + right);
                    }
                });

        process.print();

        process.getSideOutput(leftTag).printToErr("left");
        process.getSideOutput(rightTag).printToErr("right");

        env.execute();
    }
}
