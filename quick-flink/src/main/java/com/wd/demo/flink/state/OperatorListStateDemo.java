package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/7/11 12:01
 *
 * 案例需求：在 map 算子中计算数据的个数
 */
public class OperatorListStateDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);


        env.socketTextStream("localhost", 7777)
                .map(new MyCountMapFunction())
                .print();


        env.execute();
    }

    public static class MyCountMapFunction implements MapFunction<String, Long>, CheckpointedFunction {

        private Long count = 0L;

        @Override
        public Long map(String value) throws Exception {
            return count++;
        }

        /**
         * 本地变量持久化， 将本地变量拷贝到算子状态中
         * @param context the context for drawing a snapshot of the operator
         * @throws Exception
         */
        @Override
        public void snapshotState(FunctionSnapshotContext context) throws Exception {

        }

        /**
         * 初始化本地变量， 从状态中把数据添加到本地变量， 每个子任务调用一次
         * @param context the context for initializing the operator
         * @throws Exception
         */
        @Override
        public void initializeState(FunctionInitializationContext context) throws Exception {

        }
    }
}
