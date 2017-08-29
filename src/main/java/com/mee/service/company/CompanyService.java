package com.mee.service.company;

import com.mee.entity.Company;
import com.mee.entity.Order;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    List<Order> getSchedule(String date, String idCompany);
}
