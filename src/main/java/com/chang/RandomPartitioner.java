package com.chang;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Random;

/**
 * 随机分区
 */
public class RandomPartitioner implements Partitioner {
    Random random =new Random();
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        Integer partition_num = cluster.partitionCountForTopic(topic);

        return random.nextInt(partition_num);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
