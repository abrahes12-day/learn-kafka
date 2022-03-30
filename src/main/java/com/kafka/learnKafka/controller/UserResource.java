package com.kafka.learnKafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.learnKafka.model.KafkaModel;


@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	KafkaTemplate <String, KafkaModel> kafka;
	
	@Autowired
	public KafkaModel kafkaModel; 
	
	public static final String TOPIC = "kafka-topic-1";
	
	@PostMapping("/kafka/v1/message")
	public ResponseEntity<Object> dataPost(@RequestBody String message) {
		
		KafkaModel sendMessage = new KafkaModel();
		sendMessage.setMessage(message);
		
		kafka.send(TOPIC, sendMessage);
		
		return new ResponseEntity<Object>("Publish Successfully...", HttpStatus.OK);
	}
	

	
}
