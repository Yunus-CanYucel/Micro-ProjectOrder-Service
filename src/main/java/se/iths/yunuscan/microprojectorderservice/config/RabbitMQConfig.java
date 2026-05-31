package se.iths.yunuscan.microprojectorderservice.config;


import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Queue;

@Configuration
public class RabbitMQConfig {

    private static final String ORDER_QUEUE_NAME = "que-confirmation";
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonjsonMessageConverter();
    }
}
