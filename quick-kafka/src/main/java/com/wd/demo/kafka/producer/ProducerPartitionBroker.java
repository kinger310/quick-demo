package com.wd.demo.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/7/19 22:47
 */
public class ProducerPartitionBroker {
    public static void main(String[] args) {
        // 普通异步发送
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        // 分区1 AR副本都不可用， 此时kafka无法发送partition=1的消息
        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord<>("test2", 0,  "key", "wdhr" + i));
        }
        producer.close();

    }
}
