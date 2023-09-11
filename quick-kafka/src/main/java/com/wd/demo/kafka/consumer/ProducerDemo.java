package com.wd.demo.kafka.consumer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/7/19 22:47
 */
public class ProducerDemo {
    public static void main(String[] args) throws InterruptedException {
        // 普通异步发送
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<>("test2", "wd" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        System.out.printf("topic =%s, partition = %s%n",
                                metadata.topic(), metadata.partition());

                    } else {
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(1);
        }
        producer.close();
    }
}
