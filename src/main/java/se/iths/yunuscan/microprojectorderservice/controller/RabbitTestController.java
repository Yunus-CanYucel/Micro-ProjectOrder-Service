package se.iths.yunuscan.microprojectorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.yunuscan.microprojectorderservice.dto.OrderEmailMessageDTO;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class RabbitTestController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send-email-message")
    public String sendTestMessage() {
        OrderEmailMessageDTO message = new OrderEmailMessageDTO(
                "yunuscan0608@gmail.com",
                "TEST: Order-Service -> RabbitMQ -> Mail-Service fungerar!"
        );
        rabbitTemplate.convertAndSend("confirmation-queue", message);

        return "Message sent to RabbitMQ";
    }
}