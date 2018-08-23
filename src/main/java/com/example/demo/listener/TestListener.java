package com.example.demo.listener;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;


@Component
public class TestListener {
	
	private static final String USER_NAME = "admin";
	
	private static final String PASSWORD = "admin";
	
	private static final String BROKERURL = "tcp://localhost:61616";
	
	private static final String QUEUE_NAME = "com.example.activeMQ.test";
	
	public static void main(String[] args) {
		listening();
	}

	public static void listening() {
		System.out.println("监听开始=====================");
		Connection connection = null;
		Session session = null;
		try {
			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKERURL);
			//创建连接
			connection = activeMQConnectionFactory.createConnection();
			//启动连接
			connection.start();
			//创建session
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			//创建队列
			Queue queue = session.createQueue(QUEUE_NAME);
			//创建消费者
			MessageConsumer consumer = session.createConsumer(queue);
			//消费消息
			//接收
			while(true) {
				TextMessage receive = (TextMessage) consumer.receive();
				if(null != receive) {
					//获取消息
					String message = receive.getText();
					receive.acknowledge();
					System.out.println(message);
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			if(null != session) {
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if(null != connection) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
