package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.state.AggregatingState;
import org.apache.flink.api.common.state.AggregatingStateDescriptor;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
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
 * 案例需求：每种传感器水位值的平均水位
 */
public class KeyedAggregatingStateDemo {

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
                    AggregatingState<Integer, Float> vcAggState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        // TODO 2 open 初始化状态
                        // 状态描述器，name, 存储类型
                        vcAggState = getRuntimeContext().getAggregatingState(
                                new AggregatingStateDescriptor<Integer, Tuple2<Integer, Integer>, Float>(
                                        "vcAggState", new AggregateFunction<Integer, Tuple2<Integer, Integer>, Float>() {
                                    @Override
                                    public Tuple2<Integer, Integer> createAccumulator() {
                                        return Tuple2.of(0,0);
                                    }

                                    @Override
                                    public Tuple2<Integer, Integer> add(Integer value, Tuple2<Integer, Integer> accumulator) {
                                        return Tuple2.of(accumulator.f0+value, accumulator.f1+1);
                                    }

                                    @Override
                                    public Float getResult(Tuple2<Integer, Integer> accumulator) {
                                        return  1.0f * accumulator.f0 / accumulator.f1;
                                    }

                                    @Override
                                    public Tuple2<Integer, Integer> merge(Tuple2<Integer, Integer> a, Tuple2<Integer, Integer> b) {
                                        return null;
                                    }
                                }, Types.TUPLE(Types.INT, Types.INT)));
                    }

                    @Override
                    public void processElement(WaterSensor value, KeyedProcessFunction<String, WaterSensor, String>.Context ctx, Collector<String> out) throws Exception {
                        vcAggState.add(value.getVc());

                        out.collect(String.format("sensor id = %s, vcMean = %f", value.getId(), vcAggState.get()));

                    }
                }).print();

        env.execute();
    }
}
