package com.mee.repository;

import com.mee.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ workDay: {\"$eq\": ?0}, companyId: ?1 }")
    List<Order> findByWorkDayAndCompanyId(Date date, String companyId);
}
