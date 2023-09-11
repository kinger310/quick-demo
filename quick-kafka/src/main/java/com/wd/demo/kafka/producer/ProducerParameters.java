package com.wd.demo.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/7/20 10:04
 */
public class ProducerParameters {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // p.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.wd.demo.kafka.producer.MyPartitioner");

        // 缓冲区大小 64m
        p.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 67109964);
        // 批次大小 32k
        p.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);
        // 等待时间 100ms
        p.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        // 压缩
        p.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);

        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("first", "wd"+i));
        }

        // 不close，发送不出来！
        kafkaProducer.close();
    }
}
