package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: wangd
 * @Date: 2023/7/11 12:01
 *
 * 案例需求：每种传感器输出最高的三个水位值
 */
public class KeyedListStateDemo {

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
                    ListState<Integer> vcListState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        // TODO 2 open 初始化状态
                        // 状态描述器，name, 存储类型
                        vcListState = getRuntimeContext().getListState(
                                new ListStateDescriptor<>("vcListState", Types.INT));
                    }

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        vcListState.add(value.getVc());
                        List<Integer> vcs = new ArrayList<>();
                        for (Integer i : vcListState.get()) {
                            vcs.add(i);
                        }
                        vcs.sort(Comparator.reverseOrder());
                        if (vcs.size() > 3) vcs = vcs.subList(0,3);
                        out.collect(vcs.toString());

                        vcListState.update(vcs);

                    }
                }).print();

        env.execute();
    }
}
