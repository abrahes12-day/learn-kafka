package com.kafka.learnKafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.learnKafka.model.KafkaModel;

public interface KafkaRepo extends JpaRepository <KafkaModel, Long>{

	@SuppressWarnings("unchecked")
	KafkaModel save (KafkaModel request);
	KafkaModel findByMessage(String message);
}
