package com.app.task.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTaskConfig {
	public static final String USER_EXCHANGE = "user.exchange";
	public static final String USER_CREATED_QUEUE = "task.user.created.queue";
	public static final String USER_CREATED_ROUTING_KEY = "user.created";
	
	// 1. On définit la connexion explicitement
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}
	
	// 2. On définit l'échange
	@Bean
	public TopicExchange userExchange() {
		return new TopicExchange(USER_EXCHANGE);
	}
	
	// 3. On définit la queue
	@Bean
	public Queue userCreatedQueue() {
		return new Queue(USER_CREATED_QUEUE);
	}
	
	// 4. On lie les deux
	@Bean
	public Binding binding(Queue userCreatedQueue, TopicExchange userExchange) {
		return BindingBuilder.bind(userCreatedQueue).to(userExchange).with(USER_CREATED_ROUTING_KEY);
	}
	
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	// 5. On crée le template en utilisant la factory définie ci-dessus
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}