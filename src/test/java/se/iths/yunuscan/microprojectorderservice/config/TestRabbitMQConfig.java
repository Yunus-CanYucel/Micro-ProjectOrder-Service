package se.iths.yunuscan.microprojectorderservice.config;


import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import org.springframework.amqp.rabbit.core.RabbitTemplate;


@TestConfiguration
public class TestRabbitMQConfig {
    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate() {
        return Mockito.mock(RabbitTemplate.class);
    }
    @Bean
    @Primary
    public RabbitAdmin rabbitAdmin() {
        return Mockito.mock(RabbitAdmin.class);
    }
}