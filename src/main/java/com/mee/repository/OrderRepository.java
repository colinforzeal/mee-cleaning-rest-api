package com.mee.repository;

import com.mee.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ workDay: {\"$gt\": ?0, \"$lt\": ?1}, companyId: ?2 }")
    List<Order> findByWorkDayAndCompanyId(Date from, Date to, String companyId);
    List<Order> findByCompanyId(String companyId);
    List<Order> findByUserId(String userId);
}
