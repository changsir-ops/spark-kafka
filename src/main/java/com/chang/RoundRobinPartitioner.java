package com.chang;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 按照轮询的方式进行分区
 */
public class RoundRobinPartitioner  implements Partitioner {

    //声明一个线程安全的计数器
    private AtomicInteger counter=new AtomicInteger();
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
      //获取分区的个数
        Integer partition_num = cluster.partitionCountForTopic(topic);
        int partition = counter.getAndIncrement() % partition_num;

        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
