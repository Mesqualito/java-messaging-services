package com.eigenbaumarkt.javamessagingservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaMessagingServicesApplication {

    public static void main(String[] args) throws Exception {


        /*

        // only embedded test ActiveMQ Server Instance
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
        .setPersistenceEnabled(false)
        .setJournalDirectory("target/data/journal")
        .setSecurityEnabled(false)
        .addAcceptorConfiguration("invm", "vm://0"));

        server.start();

         */

        SpringApplication.run(JavaMessagingServicesApplication.class, args);
    }

}
