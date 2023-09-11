package com.wd.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/7/23 17:39
 */
public class ConsumerPartition {

    public static void main(String[] args) {
        // 配置
        Properties p = new Properties();

        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");

        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        p.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 创建消费者
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);

        // 定义主题的分区
        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("test2", 0));
        kafkaConsumer.assign(topicPartitions);

        // 消费数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println("consumerRecord = " + consumerRecord);
            }
        }
    }
}
