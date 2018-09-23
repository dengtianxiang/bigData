package com.xyg.kafka.producer;


import kafka.producer.Partitioner;

/**
 * Author: Mr.Deng
 * Date: 2018/8/5
 * Desc:
 */
public class OldCustomPartitioner implements Partitioner {

    public OldCustomPartitioner() {
        super();
    }

    @Override
    public int partition(Object key, int numPartitions) {
        // 控制分区
        return 0;
    }
}

