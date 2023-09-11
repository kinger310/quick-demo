package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
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
 * 案例需求：每种传感器水位值的和
 */
public class KeyedReducingStateDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);


        SingleOutputStreamOperator<WaterSensor> sensorDS = env
                .socketTextStream("localhost", 7777)
                .map(new WaterSensorMapFunction())
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy
                                .<WaterSensor>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                                .withTimestampAssigner((element, ts) -> element.getTs() * 1000L)
                );

        sensorDS.keyBy(WaterSensor::getId)
                .process(new KeyedProcessFunction<String, WaterSensor, String>() {

                    // TODO 1 定义状态
                    ReducingState<Integer> vcReducingState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        // TODO 2 open 初始化状态
                        // 状态描述器，name, 存储类型
                        vcReducingState = getRuntimeContext().getReducingState(
                                new ReducingStateDescriptor<>("vcReducingState", Integer::sum, Types.INT));
                    }

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        vcReducingState.add(value.getVc());

                        out.collect(String.format("sensor id = %s, vcSum = %d", value.getId(), vcReducingState.get()));

                    }
                }).print();

        env.execute();
    }
}
