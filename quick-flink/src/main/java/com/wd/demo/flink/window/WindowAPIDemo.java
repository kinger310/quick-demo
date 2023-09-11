package com.wd.demo.flink.window;

import com.wd.demo.flink.source.WaterSensor;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @Author: wangd
 * @Date: 2023/7/9 16:21
 */
public class WindowAPIDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        SingleOutputStreamOperator<WaterSensor> sensorDS = env.socketTextStream("localhost", 7777).map(s -> {
            String[] data = s.split(",");
            return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
        });

        KeyedStream<WaterSensor, String> sensorKS = sensorDS.keyBy(WaterSensor::getId);

        // 基于时间的
        sensorKS.window(TumblingProcessingTimeWindows.of(Time.seconds(10)));
        sensorKS.window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(5)));
        sensorKS.window(ProcessingTimeSessionWindows.withGap(Time.seconds(10)));

        // 增量聚合
        // window.aggregate();

        // 全窗口函数


        // 基于计数器
        // sensorKS.countWindow();

        env.execute();
    }
}
