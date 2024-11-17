package com.prajjwal.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prajjwal.rabbitmq.dto.UserDto;

@Service
public class RabbitMQJsonProducer {

	
	private static final Logger log = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitmq.routing-key.json.name}")
	private String routingKey;
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendJsonMessage(UserDto user) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
		log.info("Json message sent: {}"+user.toString());
	}
}
