package com.mee.service.order;

import com.mee.dto.OrderDTO;
import com.mee.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findByCompanyId(String companyId);
    List<Order> findByUserId(String userId);
    Order save(OrderDTO order);
    Order update(Order order);
    void delete(String idOrder);
    Order findById(String orderId);
}
