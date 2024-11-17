package com.prajjwal.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.prajjwal.rabbitmq.dto.UserDto;

@Service
public class RabbitMqJsonConsumer {

	
	private static final Logger log = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
	public void consume(UserDto user) {
		log.info("Received message: "+user);
	}
}
