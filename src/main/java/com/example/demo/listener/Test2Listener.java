package com.example.demo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Test2Listener {
	
	@JmsListener(destination = "com.example.activeMQ.test2")
	public void handlerMessage(String message) {
		System.out.println(message);
	}

}
