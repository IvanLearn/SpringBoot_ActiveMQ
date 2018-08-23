package com.example.demo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.sender.CommonMqSender;
import com.example.demo.sender.Producer;

@RestController
public class TestMq {
	
	
	private static final String QUEUE_NAME = "com.example.activeMQ.test";
	
	@Autowired
	private CommonMqSender sender;
	
	@Autowired
	private Producer producer;
	
	@RequestMapping("/send")
	public void send() {
		sender.sendMessage(QUEUE_NAME, "hehe==================");
	}
	
	@RequestMapping("/send2")
	public void send2() {
		producer.send("test2===============");
	}

}
