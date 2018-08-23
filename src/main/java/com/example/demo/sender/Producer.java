package com.example.demo.sender;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
	private JmsTemplate template;
	
	@Autowired
	private Queue test2;
	
	public void send(String message) {
		//直接发送
		template.convertAndSend(test2, message);
	}
}
