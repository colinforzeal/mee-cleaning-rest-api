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
        order.setId("3");
        order.setCompanyId("1");
        order.setCompanyName("SunCompany");
        order.setFullName("Kiryl Ziusko & Vlad Bondar");
        order.setUserId("1");
        order.setWorkDay(LocalDate.now());
        order.setWorkHoursTo(LocalTime.now());
        order.setWorkHouseFrom(LocalTime.now());
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
