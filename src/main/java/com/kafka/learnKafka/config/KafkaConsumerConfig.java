package com.kafka.learnKafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.learnKafka.model.KafkaModel;


@Configuration
public class KafkaConsumerConfig {
	// NOT USED
	
	@Bean
	public ConsumerFactory<String, KafkaModel> ConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), 
				new JsonDeserializer<>(KafkaModel.class));
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaModel> KafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(ConsumerFactory());
		return factory;
	}
}
