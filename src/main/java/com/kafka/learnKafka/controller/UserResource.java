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
import com.kafka.learnKafka.repository.KafkaRepo;


@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	KafkaTemplate <String, KafkaModel> kafka;
	
	@Autowired
	public KafkaRepo kafkaRepository; 
	
	public static final String TOPIC = "kafka-topic-1";
	
	
	@PostMapping("/kafka/v1/message")
	public ResponseEntity<Object> dataPost(@RequestBody KafkaModel message) {
		
		KafkaModel sendMessage = new KafkaModel();
		sendMessage.setMessage(message.toString());
		this.kafkaRepository.save(message);

		KafkaModel getData = this.kafkaRepository.findByMessage(message.getMessage());
		
		kafka.send(TOPIC, getData);
		System.out.println("Isi nya = " + getData);
		
		return new ResponseEntity<Object>("Publish Successfully...", HttpStatus.OK);
	}
	

	
}
