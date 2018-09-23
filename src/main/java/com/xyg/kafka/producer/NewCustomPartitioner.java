package com.xyg.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import java.util.Map;

/**
 * Author: Mr.Deng
 * Date: 2018/8/5
 * Desc: 自定义分区
 */
public class NewCustomPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
    }
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 控制分区
        return 0;
    }
    @Override
    public void close() {
    }
}