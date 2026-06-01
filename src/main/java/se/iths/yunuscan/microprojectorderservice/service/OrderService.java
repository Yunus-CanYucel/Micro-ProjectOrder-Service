package se.iths.yunuscan.microprojectorderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.yunuscan.microprojectorderservice.client.ProductClient;
import se.iths.yunuscan.microprojectorderservice.dto.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import se.iths.yunuscan.microprojectorderservice.exception.OrderCreationException;
import se.iths.yunuscan.microprojectorderservice.model.*;
import se.iths.yunuscan.microprojectorderservice.repository.OrderRepository;



@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final RabbitTemplate rabbitTemplate;

    @Value("${RABBITMQ_QUEUE:confirmation-queue}")
    private String orderEmailQueueName;

    public OrderResponseDTO createOrder(CreateOrderRequestDTO request, Jwt jwt) {
        String customerEmail = jwt.getSubject();

        List<ProductStockRequestDTO> stockRequests = new ArrayList<>();

        for (CreateOrderItemRequestsDTO item : request.items()) {
            ProductStockRequestDTO stockRequest = new ProductStockRequestDTO(
                    item.productId(),
                    item.quantity()
            );

            stockRequests.add(stockRequest);
        }

        List<ProductStockResponseDTO> productResponses =
                productClient.decreaseStock(stockRequests, jwt.getTokenValue());

        if (productResponses == null || productResponses.isEmpty()) {
            throw new OrderCreationException("No response from product-service.");
        }

        for (ProductStockResponseDTO stockResponse : productResponses) {
            if (!"SUCCESS".equalsIgnoreCase(stockResponse.status())) {
                throw new OrderCreationException(stockResponse.message());
            }
        }

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUsername(customerEmail);
        order.setTotalPrice(BigDecimal.ZERO);

        for (ProductStockResponseDTO stockResponse : productResponses) {
            OrderItem orderItem = new OrderItem();

            orderItem.setName(stockResponse.name());
            orderItem.setPrice(stockResponse.price());
            orderItem.setQuantity(stockResponse.requestedQuantity());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);

            BigDecimal itemTotal = stockResponse.price()
                    .multiply(BigDecimal.valueOf(stockResponse.requestedQuantity()));

            order.setTotalPrice(order.getTotalPrice().add(itemTotal));
        }

        Order savedOrder = orderRepository.save(order);

        OrderEmailMessageDTO emailMessage = new OrderEmailMessageDTO(
                customerEmail,
                createEmailMessage(savedOrder)
        );

        rabbitTemplate.convertAndSend(orderEmailQueueName, emailMessage);

        return toResponseDTO(savedOrder);
    }

    public List<OrderResponseDTO> findMyOrders(Jwt jwt) {
        List<Order> orders = orderRepository.findByUsername(jwt.getSubject());

        List<OrderResponseDTO> responseList = new ArrayList<>();

        for (Order order : orders) {
            OrderResponseDTO responseDTO = toResponseDTO(order);
            responseList.add(responseDTO);
        }

        return responseList;
    }

    private String createEmailMessage(Order order) {
        StringBuilder message = new StringBuilder();

        message.append("Thank you for your order. ");
        message.append("Order ID: ").append(order.getId()).append(". ");

        for (OrderItem item : order.getOrderItems()) {
            BigDecimal itemTotal = item.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            message.append(item.getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(itemTotal)
                    .append(". ");
        }

        message.append("Total price: ").append(order.getTotalPrice());

        return message.toString();
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        List<OrderItemResponseDTO> itemResponses = new ArrayList<>();

        for (OrderItem item : order.getOrderItems()) {
            OrderItemResponseDTO itemResponse = new OrderItemResponseDTO(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQuantity()
            );

            itemResponses.add(itemResponse);
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getOrderDate(),
                order.getUsername(),
                itemResponses,
                order.getTotalPrice()
        );
    }
}