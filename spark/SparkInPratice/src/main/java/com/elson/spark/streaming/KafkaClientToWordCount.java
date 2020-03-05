package com.elson.spark.streaming;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class KafkaClientToWordCount {

    public static void main(String args[]) throws InterruptedException {
        if (args.length < 2) {
            //参数
            //<metadataBrokerList> broker地址
            //<topic> topic名称
            //<messagesPerSec> 每秒产生的消息
            //<wordsPerMessage> 每条消息包括的单词数
            System.err.println("Usage:KafkaProducer <metadataBrokerList> <topic>");
            System.exit(0);
        }
        //zookeeper连接属性
        Map props = new HashMap<String, String>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, args[0]);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        SparkConf sparkConf = new SparkConf().setMaster("local[1]")
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .setAppName("KafkaClientToWordCount");

        SparkContext sc = new SparkContext(sparkConf);

        //通过zookeeper建立kafka的producer
        KafkaProducer<String, String> producer = new KafkaProducer(props);

        System.out.println("ReadTest, Please Enter Data:");
        InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象
        //通过producer发送一些消息
        //拿构造的方法传到BufferedReader中，此时获取到的就是整个缓存流
        try (BufferedReader br = new BufferedReader(is)) {
            //该方法中有个IOExcepiton需要捕获
            while (true) {
                String inputContent = br.readLine();
                System.out.println("ReadTest Output:" + inputContent);
                if (inputContent == null) {
                    continue;
                }
                if ("exit".equals(inputContent)) {
                    break;
                }
                callFireAndforget(producer, args[1], inputContent);
                synchronousSend(producer, args[1], inputContent);
                asynchronousSend(producer, args[1], inputContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        producer.close();
    }

    private static void callFireAndforget(KafkaProducer<String, String> producer, String topic, String inputContent) {
        //注意，我们这里发送的消息都是以键值对的形式发送的
        //需要把消息内容和topic封装到ProducerRecord中再发送
        //我们这里的topic为外部的传参，消息的键值对为<null,str>
        ProducerRecord message = new ProducerRecord<String, String>(topic, null, inputContent);
        //发送消息
        producer.send(message);
    }

    private static void synchronousSend(KafkaProducer<String, String> producer, String topic, String inputContent) {
        //注意，我们这里发送的消息都是以键值对的形式发送的
        //需要把消息内容和topic封装到ProducerRecord中再发送
        //我们这里的topic为外部的传参，消息的键值对为<null,str>
        ProducerRecord message = new ProducerRecord<String, String>(topic, null, inputContent);
        //发送消息
        RecordMetadata rec = null;
        try {
            rec = (RecordMetadata) producer.send(message).get();
            System.out.println("rec->" + rec.topic());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void asynchronousSend(KafkaProducer<String, String> producer, String topic, String inputContent) {
        //注意，我们这里发送的消息都是以键值对的形式发送的
        //需要把消息内容和topic封装到ProducerRecord中再发送
        //我们这里的topic为外部的传参，消息的键值对为<null,str>
        ProducerRecord message = new ProducerRecord<String, String>(topic, null, inputContent);
        try {
            System.out.println("Staring Sending....");
            producer.send(message, (RecordMetadata metadata, Exception exception) -> {
                System.out.println("Got FeedBack....");
                System.out.println("metadata->" + metadata);
            });
            System.out.println("Stop Sending....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();

        }
    }

}