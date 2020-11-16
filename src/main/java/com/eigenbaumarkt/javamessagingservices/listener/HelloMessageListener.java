package com.eigenbaumarkt.javamessagingservices.listener;

import com.eigenbaumarkt.javamessagingservices.config.JmsConfig;
import com.eigenbaumarkt.javamessagingservices.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    // listen to the queue named "my-hello-world" and when there is a
    // message, send the message to the given method
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {

        // System.out.println("I got a message!");

        // System.out.println(helloWorldMessage);

        // ToDo: use "headers" and "message" for additional service (logging etc.)

        // only for testing properties like JMSXDeliveryCount, jms_redelivered (...) in the debugger:
        // throw new RuntimeException("some exception... use your debugger!");
    }

    @JmsListener(destination = JmsConfig.MY_SAR_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers,
                               Message jmsMessage,
                               org.springframework.messaging.Message springMessage) throws JMSException {

        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();
        // with the 'javax.jms.Message' Implementation:
        jmsTemplate.convertAndSend(jmsMessage.getJMSReplyTo(), payloadMsg);

        // with the Spring framework 'Message' implementation, including cast to JMS 'Destination':
        // jmsTemplate.convertAndSend((Destination) springMessage.getHeaders().get("jms_replyTo", "got it!");



    }


}
