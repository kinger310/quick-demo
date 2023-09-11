package com.wd.demo.flink.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: wangd
 * @Date: 2023/7/8 18:45
 */
public class FileSourceDemo {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        FileSource<String> source =
                FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("quick-flink/src/main/resources/word.txt"))
                        .build();

        DataStreamSource<String> fileSource = env.fromSource(source, WatermarkStrategy.noWatermarks(), "fileSource");
        fileSource.print();

        env.execute();
    }
}
