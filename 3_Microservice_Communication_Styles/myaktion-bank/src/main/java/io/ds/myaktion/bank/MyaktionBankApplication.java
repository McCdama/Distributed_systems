package io.ds.myaktion.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.ds.myaktion.bank.dto.Transaction;

@SpringBootApplication
public class MyaktionBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyaktionBankApplication.class, args);
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("processTransaction"));
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(TransactionReceiver receiver) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, "receiveTransaction");
		messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(Transaction.class));
		return messageListenerAdapter;
	}

	@Bean
	TransactionReceiver receiver() {
		return new TransactionReceiver();
	}

}
