package com.chang;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.Properties;

public class Myproducer {
    public static void main(String[] args) {
        Producer producer=null;
        Properties properties =new Properties();
        try {
            //加载配置文件
            properties.load(Myproducer.class.getClassLoader().getResourceAsStream("producer.properties"));
           producer =new KafkaProducer(properties);

            //发送数据
            ProducerRecord<Integer,String> record =null;
            for (int i=90;i<100;i++){
                /**
                 * 参数1是指定的topic
                 * 参数2 是key
                 * 参数3 是value
                 */
                record=new ProducerRecord<>("hadoop", i, i+"hello nihao ");
                producer.send(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       //关闭资源
        producer.close();

    }
}
