package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.time.Duration;

/**
 * @Author: wangd
 * @Date: 2023/7/11 12:01
 *
 * 案例需求：每种传感器输出每种水位值出现的次数
 */
public class KeyedMapStateDemo {

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
                    MapState<Integer, Integer> vcMapState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        // TODO 2 open 初始化状态
                        // 状态描述器，name, 存储类型
                        vcMapState = getRuntimeContext().getMapState(
                                new MapStateDescriptor<>("vcMapState", Types.INT,Types.INT));

                    }

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        Integer vc = value.getVc();
                        Integer count = vcMapState.get(vc);
                        if (!vcMapState.contains(vc)) {
                            vcMapState.put(vc, 1);
                        } else {
                            vcMapState.put(vc, ++count);
                        }
                        StringBuilder outStr = new StringBuilder();
                        outStr.append("传感器id=").append(value.getId()).append(",");
                        vcMapState.entries().forEach(e -> outStr.append("vc = "+ e.getKey() + ",count = " + e.getValue() + ";"));

                        out.collect(outStr.toString());


                        // vcMapState.get();
                        // vcMapState.contains();
                        // vcMapState.put();
                        // vcMapState.putAll();
                        // vcMapState.entries();
                        // vcMapState.isEmpty();
                        // vcMapState.iterator();
                        // vcMapState.keys();
                        // vcMapState.values();
                        // vcMapState.remove();
                    }
                }).print();

        env.execute();
    }
}
