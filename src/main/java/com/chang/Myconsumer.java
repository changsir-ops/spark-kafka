package com.chang;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Myconsumer {
    public static void main(String[] args) {
        Consumer consumer=null;
        Properties properties=new Properties();
        try {
            //加载配置文件
            properties.load(Myconsumer.class.getClassLoader().getResourceAsStream("consumer.properties"));
            consumer = new KafkaConsumer(properties);
            /*订阅topic,因为 public void subscribe(Collection<String> topics);接收的是一个集合的类型
            *所以要对topic的封装在集合中
             */
            List<String> list=new ArrayList<>();
            list.add("hadoop");
            consumer.subscribe(list);
                //循环接收
            while (true){
                //消费,指定拉取的时间间隔
                ConsumerRecords<Integer, String> records = consumer.poll(1000);
            for (ConsumerRecord<Integer, String> record : records) {
                //获取消息的属性并打印
                String topic = record.topic();
                int partition = record.partition();
                Integer key = record.key();
                String value = record.value();
                long offset = record.offset();
                System.out.println(String.format("topic %s\t,partition %d\t,key:%d\t,value:%s\t,offset:%d\t",
                        topic, partition, key, value, offset));

            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           // consumer.close();
        }
    }
}
