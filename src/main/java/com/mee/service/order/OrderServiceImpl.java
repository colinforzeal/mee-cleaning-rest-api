package com.mee.service.order;

import com.mee.dto.OrderDTO;
import com.mee.entity.Company;
import com.mee.entity.Order;
import com.mee.entity.User;
import com.mee.repository.CompanyRepository;
import com.mee.repository.OrderRepository;
import com.mee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Order> findByCompanyId(String companyId) {
        return orderRepository.findByCompanyId(companyId);
    }

    public List<Order> findByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order save(OrderDTO orderDTO) {
        Order order = getDataFromDTO(orderDTO);

        return orderRepository.insert(order);
    }

    public Order update(OrderDTO orderDTO) {
        if(orderDTO.getId() == null) {
            return new Order();
        }
        Order order = getDataFromDTO(orderDTO);
        System.err.println(order);
        return orderRepository.save(order);
    }

    public void delete(String idOrder) {
        orderRepository.delete(idOrder);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findOne(orderId);
    }

    private Order getDataFromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());
        order.setCompanyId(orderDTO.getCompanyId());
        User user = userRepository.findOne(orderDTO.getUserId());
        String fullName = user.getFirstName()+" "+ user.getLastName();
        order.setFullName(fullName);
        Company company = companyRepository.findOne(orderDTO.getCompanyId());
        order.setCompanyName(company.getId());

        LocalDate date = orderDTO.getStartsAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime workHoursFrom = orderDTO.getStartsAt().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime workHoursTo = orderDTO.getEndsAt().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        order.setWorkHoursFrom(workHoursFrom);
        order.setWorkHoursTo(workHoursTo);
        order.setWorkDay(date);

        return order;
    }
}
