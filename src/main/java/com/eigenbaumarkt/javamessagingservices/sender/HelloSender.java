package com.eigenbaumarkt.javamessagingservices.sender;

import com.eigenbaumarkt.javamessagingservices.config.JmsConfig;
import com.eigenbaumarkt.javamessagingservices.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    // fixed schedule every 2000 ms
    @Scheduled(fixedRate = 2000)
    public void sendMessage() {

        System.out.println("I'm sending a message.");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message sent!");

    }

}
