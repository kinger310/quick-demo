package com.wd.demo.flink.watermark;

import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangd
 * @Date: 2023/7/9 23:09
 */
public class MyPeriodWatermarkGenerator<T> implements WatermarkGenerator<T> {
    private static final Logger log = LoggerFactory.getLogger(MyPeriodWatermarkGenerator.class);
    private long delayTs;

    private long maxTs;


    public MyPeriodWatermarkGenerator(long delayTs) {
        this.delayTs = delayTs;
        this.maxTs = Long.MIN_VALUE + delayTs + 1;
    }

    @Override
    public void onEvent(T event, long eventTimestamp, WatermarkOutput output) {
        maxTs = Math.max(maxTs, eventTimestamp);
        System.out.println("onEvent called, maxTs = " + maxTs);
    }

    @Override
    public void onPeriodicEmit(WatermarkOutput output) {
        output.emitWatermark(new Watermark(maxTs - delayTs -1));
        log.warn("onPeriodicEmit called, watermark = {}", maxTs - delayTs -1);
    }
}
