package com.eigenbaumarkt.javamessagingservices.listener;

import com.eigenbaumarkt.javamessagingservices.config.JmsConfig;
import com.eigenbaumarkt.javamessagingservices.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    // listen to the queue named "my-hello-world" and when there is a
    // message, send the message to the given method
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) {

        System.out.println("I got a message!");

        System.out.println(helloWorldMessage);

        // ToDo: use "headers" and "message" for additional service (logging etc.)

        throw new RuntimeException("some exception... use your debugger!");
        // see additional properties in the debugger (like JMSXDeliveryCount, jms_redelivered ...) -
        // fast & rock-solid JMS cares for transaction: if message can't be delivered to client, it will be redelivered

    }

}
