package com.jotacode.pizza.service;

import com.jotacode.pizza.persistence.entity.OrderEntity;
import com.jotacode.pizza.persistence.projection.OrderSummary;
import com.jotacode.pizza.persistence.repository.OrderRepository;
import com.jotacode.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRY_OUT = "C";
    private static final String ONSITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    //query method
    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0,0);
        return orderRepository.findAllByDateBefore(today);
    }

    //query method
    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = List.of(DELIVERY, CARRY_OUT);
        return orderRepository.findAllByMethodIn(methods);
    }

    //query native method
    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return orderRepository.findCustomerOrder(idCustomer);
    }

    public OrderSummary getOrderSummary(int orderId){
        return orderRepository.findSummary(orderId);
    }


    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto){
        return orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }

}
