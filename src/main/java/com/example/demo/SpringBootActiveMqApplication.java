package com.example.demo;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringBootActiveMqApplication {
	
	@Bean("test2")
	public Queue getQueue() {
		return new ActiveMQQueue("com.example.activeMQ.test2");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMqApplication.class, args);
	}
}
