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
public class ProducerTxn {
    public static void main(String[] args) {
        // 普通异步发送
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //必须指定事务id
        p.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "wd-kafka");

        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        producer.initTransactions();

        producer.beginTransaction();

        try {
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>("test2", "wd " + i));
            }
            // int i = 1/0;
            producer.commitTransaction();
        } catch (Exception e) {
            producer.abortTransaction();
            throw new RuntimeException(e);
        } finally {
            producer.close();
        }
    }
}
