package com.prajjwal.rabbitmq.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prajjwal.rabbitmq.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class StringMessageController {

	private RabbitMQProducer producer;;

	public StringMessageController(RabbitMQProducer rabbitMQProducer) {
		this.producer = rabbitMQProducer;
	}
	
	@GetMapping("/publish")
	public ResponseEntity<?>  publishStringMessage(@RequestParam("msg") String param) {
		producer.sendMessage(param);
		return ResponseEntity.noContent().build();
		
	}
	
}
