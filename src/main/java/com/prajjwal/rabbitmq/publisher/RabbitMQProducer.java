package com.prajjwal.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	private static final Logger log  = LoggerFactory.getLogger(RabbitMQProducer.class);
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitmq.routing-key.name}")
	private String routingKey;
	
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(String messsage) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, messsage);
		log.info("Message sent to procer");
	}
	
}
