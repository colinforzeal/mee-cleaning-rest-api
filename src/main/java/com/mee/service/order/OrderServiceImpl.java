package com.mee.service.order;

import com.mee.entity.Order;
import com.mee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> findByCompanyId(String companyId) {
        return orderRepository.findByCompanyId(companyId);
    }

    public List<Order> findByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public void delete(String idOrder) {
        orderRepository.delete(idOrder);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findOne(orderId);
    }
}
