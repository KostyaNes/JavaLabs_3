package com.javalabs.eclient;

import com.javalabs.eclient.entity.Car;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {
    private final static String CREATE_TOPIC = "create.entity";
    private final static String UPDATE_TOPIC = "update.entity";
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Producer<Integer, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }

    public static void sendCreateMessage(Car car){
        final Producer<Integer, String> producer = createProducer();

        try {
            final ProducerRecord<Integer, String> record = new ProducerRecord<>(
                    CREATE_TOPIC, car.getId(), car.getModel());
            RecordMetadata metadata = producer.send(record).get();
            System.out.printf(
                    "sent record(key=%s value=%s) meta(partition=%d, offset=%d)",
                    record.key(),
                    record.value(),
                    metadata.partition(),
                    metadata.offset()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            producer.flush();
            producer.close();
        }
    }

    public static void sendUpdateMessage(Car car){
        final Producer<Integer, String> producer = createProducer();

        try {
            final ProducerRecord<Integer, String> record = new ProducerRecord<>(
                    UPDATE_TOPIC, car.getId(), car.getModel());
            RecordMetadata metadata = producer.send(record).get();
            System.out.printf(
                    "sent record(key=%s value=%s) meta(partition=%d, offset=%d)",
                    record.key(),
                    record.value(),
                    metadata.partition(),
                    metadata.offset()
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
