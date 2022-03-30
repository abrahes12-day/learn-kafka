package com.kafka.learnKafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.learnKafka.model.KafkaModel;

@Service
public class KafkaConsummerListener {

	@KafkaListener(topics = "kafka-topic-1", groupId = "group_json",
			containerFactory = "KafkaListenerFactory")
	public void consume(KafkaModel kafka) {
		System.out.println("Consumed message: " + kafka.getMessage());
	}
	
	
}
