package com.wd.demo.flink.split;

import com.wd.demo.flink.source.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SideOutputDataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @Author: wangd
 * @Date: 2023/7/8 23:12
 */
public class SideOutputDemo {
    public static void main(String[] args) throws Exception {
        // StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        env.setParallelism(1);

        DataStreamSource<String> source = env.socketTextStream("localhost", 7777);
        SingleOutputStreamOperator<WaterSensor> sensor = source.map(value -> {
            String[] data = value.split(",");
            return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
        });

        // 需求： watersensor的数据，s1, s2分开
        OutputTag<WaterSensor> s1 = new OutputTag<>("s1", Types.POJO(WaterSensor.class));
        OutputTag<WaterSensor> s2 = new OutputTag<>("s2", Types.POJO(WaterSensor.class));

        SingleOutputStreamOperator<WaterSensor> process = sensor.process(new ProcessFunction<WaterSensor, WaterSensor>() {
            @Override
            public void processElement(WaterSensor value, ProcessFunction<WaterSensor, WaterSensor>.Context ctx, Collector<WaterSensor> out) throws Exception {
                String id = value.getId();
                if ("s1".equals(id)) {
                    // 输出到s1
                    ctx.output(s1, value);
                } else if ("s2".equals(id)) {
                    // 输出到s2
                    ctx.output(s2, value);
                } else {
                    // 主流
                    out.collect(value);
                }
            }
        });
        // 打印主流
        process.print("main");
        
        //
        SideOutputDataStream<WaterSensor> s1Output = process.getSideOutput(s1);
        SideOutputDataStream<WaterSensor> s2Output = process.getSideOutput(s2);

        s1Output.print("s1");
        s2Output.print("s2");

        env.execute();
    }
    
}
