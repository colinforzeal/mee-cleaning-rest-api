package com.mee.repository;

import com.mee.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void save() {
        Order order = new Order();
        order.setId("4");
        order.setCompanyId("3");
        order.setCompanyName("FiveStarCleaning");
        order.setUserId("2");
        order.setFullName("Oleg Raptunov");
        order.setWorkDay(LocalDate.now());
        order.setWorkHoursTo(LocalTime.now().plusHours(4));
        order.setWorkHoursFrom(LocalTime.now().plusHours(3));
        orderRepository.save(order);
    }

    @Test
    public void getById() {
        Order order = orderRepository.findOne("1");
        System.out.println(order);
    }

    @Test
    public void deleteById() {
        orderRepository.delete("abcdefgh");
    }

    @Test
    public void updateById() {
        Order order = orderRepository.findOne("1");
        order.setFullName("UpdatedValue");
        orderRepository.save(order);
        System.out.println(order);
    }
}
