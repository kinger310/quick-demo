package com.wd.demo.flink.combine;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangd
 * @Date: 2023/7/9 10:40
 */
public class ConnectKeyByDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setParallelism(2);

        DataStreamSource<Tuple2<Integer, String >> source1 = env.fromElements(
                Tuple2.of(1, "a1"),
                Tuple2.of(1, "a2"),
                Tuple2.of(2, "b"),
                Tuple2.of(3, "c")
        );
        DataStreamSource<Tuple3<Integer, String, Integer>> source2 = env.fromElements(
                Tuple3.of(1, "aa1", 1),
                Tuple3.of(1, "aa2", 2),
                Tuple3.of(2, "bb", 1),
                Tuple3.of(3, "cc", 1)
        );
        ConnectedStreams<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>> connect = source1.connect(source2);


        SingleOutputStreamOperator<String> process = connect
                .keyBy(0, 0)
                .process(new CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>() {
            Map<Integer, List<Tuple2<Integer, String>>> s1Cache = new HashMap<>();
            Map<Integer, List<Tuple3<Integer, String, Integer>>> s2Cache = new HashMap<>();

            @Override
            public void processElement1(Tuple2<Integer, String> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>.Context ctx, Collector<String> out) throws Exception {
                Integer id = value.f0;
                if (!s1Cache.containsKey(id)) {
                    List<Tuple2<Integer, String>> values = new ArrayList<>();
                    values.add(value);
                    s1Cache.put(id, values);
                } else {
                    s1Cache.get(id).add(value);
                }
                // 去 s2Cache中查找是否存在id匹配输出
                if (s2Cache.containsKey(id)) {
                    for (Tuple3<Integer, String, Integer> t : s2Cache.get(id)) {
                        out.collect(String.format("s1: %s, s2: %s", value, t));
                    }
                }
            }

            @Override
            public void processElement2(Tuple3<Integer, String, Integer> value, CoProcessFunction<Tuple2<Integer, String>, Tuple3<Integer, String, Integer>, String>.Context ctx, Collector<String> out) throws Exception {
                Integer id = value.f0;
                if (!s2Cache.containsKey(id)) {
                    List<Tuple3<Integer, String, Integer>> values = new ArrayList<>();
                    values.add(value);
                    s2Cache.put(id, values);
                } else {
                    s2Cache.get(id).add(value);
                }
                // 去 s1Cache中查找是否存在id匹配输出
                if (s1Cache.containsKey(id)) {
                    for (Tuple2<Integer, String> t : s1Cache.get(id)) {
                        out.collect(String.format("s1: %s, s2: %s", t, value));
                    }
                }
            }
        });

        process.print();

        env.execute();
    }
}
