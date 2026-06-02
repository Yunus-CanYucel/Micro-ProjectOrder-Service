package se.iths.yunuscan.microprojectorderservice.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;



@Configuration
public class RabbitMQConfig {

    @Value("${RABBITMQ_QUEUE}")
    private String orderEmailQueueName;
    @Bean
    public Queue orderEmailQueue() {
        return new Queue(orderEmailQueueName, true);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
