package com.wd.demo.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * @Author: wangd
 * @Date: 2023/7/23 17:39
 */
public class ConsumerCommitTimestamp {

    public static void main(String[] args) {
        // 配置
        Properties p = new Properties();

        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");

        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        p.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 创建消费者
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(p);

        // 定义主题
        ArrayList<String> topics = new ArrayList<>();
        topics.add("test2");
        kafkaConsumer.subscribe(topics);

        Set<TopicPartition> assignment = kafkaConsumer.assignment();
        // 确保分区分配方案已经制定完毕
        while (assignment.size() == 0) {
            kafkaConsumer.poll(Duration.ofSeconds(1));
            assignment = kafkaConsumer.assignment();
        }

        // kafkaConsumer.offsetsForTimes()

        Map<TopicPartition, Long> map = new HashMap<>();
        for (TopicPartition topicPartition : assignment) {
            map.put(topicPartition, System.currentTimeMillis() - 1*24*3600*1000);
        }
        Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes = kafkaConsumer.offsetsForTimes(map);


        for (TopicPartition topicPartition : assignment) {
            OffsetAndTimestamp offsetAndTimestamp = offsetsForTimes.get(topicPartition);
            kafkaConsumer.seek(topicPartition, offsetAndTimestamp.offset());
        }

        // 消费数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println("consumerRecord = " + consumerRecord);
            }

        }
    }
}
