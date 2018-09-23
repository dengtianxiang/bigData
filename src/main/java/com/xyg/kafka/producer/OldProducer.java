package com.xyg.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import java.util.Properties;

/**
 * Author: Mr.Deng
 * Date: 2018/8/5
 * Desc: 旧生产者API
 */
public class OldProducer {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("metadata.broker.list", "node21:9092,node22:9092,node23:9092");
        properties.put("request.required.acks", "1");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        Producer<Integer, String> producer = new Producer<Integer,String>(new ProducerConfig(properties));
        KeyedMessage<Integer, String> message = new KeyedMessage<Integer, String>("firstTopic", "hello world");
        producer.send(message );
        System.out.println(message);
    }
}