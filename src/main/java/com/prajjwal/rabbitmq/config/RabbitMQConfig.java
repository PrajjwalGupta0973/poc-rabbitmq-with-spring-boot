package com.prajjwal.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.queue.name}")
	private String queueName;
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${rabbitmq.routing-key.name}")
	private String routingKey;
	@Value("${rabbitmq.routing-key.json.name}")
	private String routingJsonKey;

	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueue;

	@Bean
	ConnectionFactory connectionFactory() {
		// TODO : externalize values
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setPort(5672);
		return connectionFactory;
	}

	@Bean
	Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	Queue jsonQueue() {
		return new Queue(jsonQueue);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("prajjwal-spring-topic-exchange");
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKey);
	}

	@Bean
	Binding jsonBinding() {
		return BindingBuilder.bind(jsonQueue()).to(topicExchange()).with(routingJsonKey);
	}

	@Bean
	MessageConverter jsonConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonConverter());
		return rabbitTemplate;
	}
}
