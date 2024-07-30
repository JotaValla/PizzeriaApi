package com.jotacode.pizza.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double gerOrderTotal();
    String getPizzasName();

}
