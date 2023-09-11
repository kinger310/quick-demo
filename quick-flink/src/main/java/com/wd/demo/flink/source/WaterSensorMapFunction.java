package com.wd.demo.flink.source;

import org.apache.flink.api.common.functions.MapFunction;

/**
 * @Author: wangd
 * @Date: 2023/7/10 12:08
 */
public class WaterSensorMapFunction implements MapFunction<String, WaterSensor> {


    @Override
    public WaterSensor map(String value) throws Exception {
        String[] data = value.split(",");
        return new WaterSensor(data[0], Long.valueOf(data[1]), Integer.valueOf(data[2]));
    }
}
