package com.eigenbaumarkt.javamessagingservices.sender;

import com.eigenbaumarkt.javamessagingservices.config.JmsConfig;
import com.eigenbaumarkt.javamessagingservices.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    // Spring Boot creates and configures an Jackson-ObjectMapper
    private final ObjectMapper objectMapper;

    // fixed schedule every 2000 ms
    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
  }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SAR_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null;
                try {
                    // no "convert" in the template method any more - we use Spring Boot Jackson ObjectMapper
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "com.eigenbaumarkt.javamessagingservices.model.HelloWorldMessage");
                    System.out.println("Sending 'Hello'");
                    return helloMessage;
                } catch (JsonProcessingException e) {
                    throw new JMSException("tilt");
                    // e.printStackTrace();
                }
            }
        });

        System.out.println(receivedMsg.getBody(String.class));

    }

}
