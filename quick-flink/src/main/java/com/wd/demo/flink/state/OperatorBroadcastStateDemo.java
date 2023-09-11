package com.wd.demo.flink.state;

import com.wd.demo.flink.source.WaterSensor;
import com.wd.demo.flink.source.WaterSensorMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Map;

/**
 * @Author: wangd
 * @Date: 2023/7/11 12:01
 *
 * 案例需求：水位超过指定的阈值发送告警，阈值可以动态修改
 */
public class OperatorBroadcastStateDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);


        SingleOutputStreamOperator<WaterSensor> sensorDS = env.socketTextStream("localhost", 7777)
                .map(new WaterSensorMapFunction());

        // 配置流 用来广播配置
        DataStreamSource<String> configDS = env.socketTextStream("localhost", 8888);
        MapStateDescriptor<String, Integer> broadcastState = new MapStateDescriptor<>("broadcast", Types.STRING, Types.INT);
        BroadcastStream<String> broadcast = configDS.broadcast(broadcastState);

        BroadcastConnectedStream<WaterSensor, String> connect = sensorDS.connect(broadcast);
        connect.process(new BroadcastProcessFunction<WaterSensor, String, String>() {
            @Override
            public void processElement(WaterSensor value, BroadcastProcessFunction<WaterSensor, String, String>.ReadOnlyContext ctx, Collector<String> out) throws Exception {
                ReadOnlyBroadcastState<String, Integer> broadcastState1 = ctx.getBroadcastState(broadcastState);
                Integer threshold = broadcastState1.get("threshold");
                threshold = threshold == null ? 0 : threshold;
                if (value.getVc() > threshold) {
                    out.collect(String.format("%s, 水位超过threshold %d", value, threshold ));
                }
            }

            @Override
            public void processBroadcastElement(String value, BroadcastProcessFunction<WaterSensor, String, String>.Context ctx, Collector<String> out) throws Exception {
                // broadcastState.ge
                BroadcastState<String, Integer> broadcastState1 = ctx.getBroadcastState(broadcastState);
                broadcastState1.put("threshold", Integer.valueOf(value));
            }
        }).print();


        env.execute();

    }

}
