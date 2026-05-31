package se.iths.yunuscan.microprojectorderservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import se.iths.yunuscan.microprojectorderservice.client.ProductClient;
import se.iths.yunuscan.microprojectorderservice.model.Order;
import se.iths.yunuscan.microprojectorderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService{
    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    public Order createOrder(){
        Order order = new Order();


        /*

    a date setter for the new order(product

    a email/user setter

    list creater to contain the items

    and price starts at 0 instead of null

    for each item in request call product service and add the product by name,price and quantity

    add those items to order list

    add just sum the total price

    add all order items to the order
    Saves total order price

     */




        // Saves order to database
        return orderRepository.save(order);
    }


}
