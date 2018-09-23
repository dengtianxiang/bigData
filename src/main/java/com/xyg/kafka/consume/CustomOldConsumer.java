package com.xyg.kafka.consume;

/**
 * Author: Mr.Deng
 * Date: 2018/8/5
 * Desc:
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class CustomOldConsumer {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("zookeeper.connect", "node21:2181,node22:2181,node23:2181");
        properties.put("group.id", "g1");
        properties.put("zookeeper.session.timeout.ms", "500");
        properties.put("zookeeper.sync.time.ms", "250");
        properties.put("auto.commit.interval.ms", "1000");
        // 创建消费者连接器
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
        HashMap<String, Integer> topicCount = new HashMap<>();
        topicCount.put("firstTopic", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCount);
        KafkaStream<byte[], byte[]> stream = consumerMap.get("firstTopic").get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();

        while (it.hasNext()) {
            System.out.println(new String(it.next().message()));
        }
    }
}
