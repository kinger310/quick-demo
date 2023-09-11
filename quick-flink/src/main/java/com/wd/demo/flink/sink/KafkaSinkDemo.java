package com.wd.demo.flink.sink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.producer.ProducerConfig;

/**
 * @Author: wangd
 * @Date: 2023/7/9 11:33
 */
public class KafkaSinkDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // env.enableCheckpointing(2000, CheckpointingMode.EXACTLY_ONCE);

        DataStreamSource<String> source = env.socketTextStream("localhost", 7777);
        // source.print();


        // 192.168.128.128
        KafkaSink<String> kafkaSink = KafkaSink
                .<String>builder()
                .setBootstrapServers("kafka0:9093,kafka1:9094,kafka2:9095")
                .setRecordSerializer(
                        KafkaRecordSerializationSchema
                                .<String>builder()
                                .setTopic("kafka_demo")
                                .setValueSerializationSchema(new SimpleStringSchema())
                                .build()
                )
                .setDeliveryGuarantee(DeliveryGuarantee.EXACTLY_ONCE)
                // 如果是精准一次，必须设置事务前缀
                // .setTransactionalIdPrefix("wd-")
                // 如果精准一次，必须设置事务超时时间大于CK间隔，小于15分钟
                .setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 10 * 60 * 1000 + "")

                .build();
        source.map(v -> {
            System.out.println("v = " + v);
            return v;
        }).sinkTo(kafkaSink);
        env.execute();

    }
}
