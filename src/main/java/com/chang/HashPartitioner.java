package com.chang;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class HashPartitioner implements Partitioner {
    /**
     *
     * @param topic 指定topic
     * @param key  key的值
     * @param keyBytes key的字节数组
     * @param value value的值
     * @param valueBytes value的字节数组
     * @param cluster  集群
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(key==null){
            return 0;
        }else{
            Integer topic_num = cluster.partitionCountForTopic(topic);
            //key的hash的值
            int key_hash = Math.abs(key.hashCode());
            System.out.println(key_hash);
            return key_hash%topic_num;
        }
        //获取指定的topic的分区的数量

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
