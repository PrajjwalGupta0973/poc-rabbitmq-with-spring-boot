package com.prajjwal.rabbitmq.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prajjwal.rabbitmq.dto.UserDto;
import com.prajjwal.rabbitmq.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {

	private RabbitMQJsonProducer producer;;

	public JsonMessageController(RabbitMQJsonProducer  producer) {
		this.producer = producer;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<?>  publishJsonMessage(@RequestBody UserDto user ) {
		producer.sendJsonMessage(user);
		return ResponseEntity.noContent().build();
	}
}
