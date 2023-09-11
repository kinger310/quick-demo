package com.wd.demo.flink.combine;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * @Author: wangd
 * @Date: 2023/7/9 10:24
 */
public class ConnectDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> source1 = env.fromElements(1, 2, 3);
        DataStreamSource<String> source2 = env.fromElements("aaa", "bbb", "ccc");
        ConnectedStreams<Integer, String> connected = source1.connect(source2);
        SingleOutputStreamOperator<String> map = connected.map(new CoMapFunction<Integer, String, String>() {
            @Override
            public String map1(Integer value) throws Exception {
                return String.valueOf(value);
            }

            @Override
            public String map2(String value) throws Exception {
                return value;
            }
        });
        map.print();
        env.execute();
    }
}
