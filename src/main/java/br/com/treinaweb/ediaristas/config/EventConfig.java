package br.com.treinaweb.ediaristas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class EventConfig {
    
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        var eventMulticarter = new SimpleApplicationEventMulticaster();
        eventMulticarter.setTaskExecutor(new SimpleAsyncTaskExecutor());

        return eventMulticarter;
    }
}
