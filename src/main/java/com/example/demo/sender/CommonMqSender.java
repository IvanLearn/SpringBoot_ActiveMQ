package com.example.demo.sender;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.springframework.stereotype.Component;


@Component
public class CommonMqSender {
	
	private static final String USER_NAME = "admin";
	
	private static final String PASSWORD = "admin";
	
	private static final String BROKERURL = "tcp://localhost:61616";
	
	public void sendMessage(String queueName , String message) {
		Connection connection = null;
		Session session = null;
		try {
			ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKERURL);
			//创建连接
			connection = activeMQConnectionFactory.createConnection();
			//启动连接
			connection.start();
			//创建session 设置消息可靠性，签收类型：手动签收
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			//根据传来的队列名称创建队列
			Queue queue = session.createQueue(queueName);
			//创建生产者
			MessageProducer producer = session.createProducer(queue);
			//创建消息
			TextMessage textMessage = session.createTextMessage(message);
			//延时发送，10s
			textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 10000);
			//消息持久化，存储在磁盘
			textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
			//推送消息到队列
			producer.send(textMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			//关闭各种连接
			if(null != session) {
				try {
					session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(null != connection) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
