package com.wd.demo.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangd
 * @Date: 2023/7/3 14:29
 */
public class WordCountStreamDemo {

    private static final Logger log = LoggerFactory.getLogger(WordCountStreamDemo.class);

    public static void main(String[] args) throws Exception {
        log.info("hi");
        // 1. 获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 2. 读取数据
        DataStreamSource<String> wordSource = env.readTextFile("quick-flink/src/main/resources/word.txt");
        // wordSource.print();
        // 3. 处理
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = wordSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] words = s.split(" ");
                for (String word : words) {
                    collector.collect(Tuple2.of(word, 1));
                }
            }
        });
        // .returns(Types.TUPLE(Types.STRING, Types.INT));
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = wordAndOne.keyBy(v -> v.f0).sum(1);

        // 4. 输出
        sum.print();

        // 5. 启动
        env.execute();
    }
}
