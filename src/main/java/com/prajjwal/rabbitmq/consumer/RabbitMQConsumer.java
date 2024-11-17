package com.prajjwal.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	public void consume(String message) {
		log.info("Received message: {} "+message);
	}
}
