package com.wd.demo.flink.combine;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: wangd
 * @Date: 2023/7/9 10:24
 */
public class UnionDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> source1 = env.fromElements(1, 2, 3);
        DataStreamSource<Integer> source2 = env.fromElements(11, 22, 33);
        DataStreamSource<String> source3 = env.fromElements("111", "222", "333");
        DataStream<Integer> union = source1.union(source2).union(source3.map(value -> Integer.valueOf(value)));
        union.print();
        env.execute();
    }
}
