package com.eigenbaumarkt.javamessagingservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

// perform tasks out of a task pool
@EnableScheduling
@EnableAsync
// @Configuration: tells Spring framework to look for Beans here...
@Configuration
public class TaskConfig {

    // a 'taskExecutor'-bean, standard part of Spring framework,
    // gives the ability to run async tasks
    @Bean
    TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


}
