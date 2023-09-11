package com.wd.demo.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: wangd
 * @Date: 2023/7/3 16:06
 */
public class SlotSharingGroupDemo {

    public static void main(String[] args) throws Exception {
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        // 读socket
        DataStreamSource<String> source = env.socketTextStream("localhost", 7777);

        env.setParallelism(1);

        source
                .flatMap((FlatMapFunction<String, String>) (s, collector) -> {
                    String[] words = s.split(" ");
                    for (String word : words) {
                        collector.collect(word);
                    }
                })
                .returns(Types.STRING)
                // 默认是default， 如果自定义了slotSharingGroup，则map不会和default放入同一个slot中。
                .map(w -> Tuple2.of(w, 1)).slotSharingGroup("map")
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(k -> k.f0)
                .sum(1)
                .print();

        env.execute();
    }
}