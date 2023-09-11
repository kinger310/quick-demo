package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
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
 * 案例需求：检测每种传感器的水位值，如果连续的两个水位值差值超过 10，就输出报警
 */
public class KeyedValueStateDemo {

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
                    ValueState<Integer> lastVcState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        // TODO 2 open 初始化状态
                        // 状态描述器，name, 存储类型
                        lastVcState = getRuntimeContext().getState(
                                new ValueStateDescriptor<>("lastVcState", Types.INT));
                    }

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {

                        int lastVc = lastVcState.value() == null ? 0 : lastVcState.value();
                        int vc =  value.getVc();
                        if (Math.abs(vc - lastVc) > 10) {
                            out.collect("传感器=" +
                                    value.getId() + "==>当前水位值=" + vc + ",与上一条水位值=" + lastVc +
                                    ",相差超过 10！！！！");
                        }
                        lastVcState.update(vc);
                    }
                }).print();

        env.execute();
    }
}
