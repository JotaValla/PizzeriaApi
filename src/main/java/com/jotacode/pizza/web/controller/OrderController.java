package com.jotacode.pizza.web.controller;

import com.jotacode.pizza.persistence.entity.OrderEntity;
import com.jotacode.pizza.persistence.projection.OrderSummary;
import com.jotacode.pizza.service.OrderService;
import com.jotacode.pizza.service.dto.RandomOrderDto;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        List<OrderEntity> orders = orderService.getAll();
        orders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return ResponseEntity.ok(orders);
    }

    //QUERY METHOD
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(orderService.getTodayOrders());
    }

    //QUERY METHOD
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(orderService.getOutsideOrders());
    }

    //QUERY NATIVE METHOD
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderSummary(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        return ResponseEntity.ok(orderService.saveRandomOrder(dto));
    }


}
