package com.eigenbaumarkt.javamessagingservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldMessage implements Serializable {

    // Implementing "Serializable" and a "serialVersionUID":
    // if the message will be send as JMS object message, the object has to be serializable
    static final long serialVersionUID = 69437894234231L;

    private UUID id;
    private String message;



}
