package com.wd.demo.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: wangd
 * @Date: 2023/7/19 22:47
 */
public class ProducerWithPartitioner {
    public static void main(String[] args) throws InterruptedException {
        // 普通异步发送
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9093,kafka1:9094,kafka2:9095");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.wd.demo.kafka.producer.MyPartitioner");

        KafkaProducer<String, String> producer = new KafkaProducer<>(p);
        for (int i = 0; i < 500; i++) {
            producer.send(new ProducerRecord<>("test2", "dw" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e == null) {
                        System.out.println(String.format("topic =%s, partition = %s" ,
                                metadata.topic(), metadata.partition()));

                    } else {
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(2);
        }
        producer.close();
    }
}
