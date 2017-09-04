package com.mee.controller;

import com.mee.dto.OrderDTO;
import com.mee.entity.Order;
import com.mee.security.auth.login.LoginAuthenticationProcessingFilter;
import com.mee.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/getByIdUser/{id}", method = RequestMethod.GET)//todo: only user
    public ResponseEntity<List<Order>> getHistoryByIdUser(@PathVariable String id) {
        logger.info("Get history order by user with id = {}", id);
        List<Order> orderList = orderService.findByUserId(id);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByIdCompany/{id}", method = RequestMethod.GET)//todo: only company
    public ResponseEntity<List<Order>> findHistoryByIdCompany(@PathVariable String id) {
        logger.info("Get history order by company with id = {}", id);
        List<Order> orderList = orderService.findByCompanyId(id);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getByIdOrder/{id}", method = RequestMethod.GET)//todo: company and user
    public ResponseEntity<Order> findOrderById(@PathVariable String id) {
        logger.info("Get order with id = {}", id);
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)//todo: only user
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO order) {
        logger.info("Save new order: {}", order);
        Order savedOrder = orderService.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)//todo: company and user?
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        logger.info("Update order with id = {}", order.getId());
        Order updatedOrder = orderService.update(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)//todo: company and user?
    public ResponseEntity<Order> deleteOrder(@PathVariable String id) {
        logger.info("Delete order with id = {}", id);
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
