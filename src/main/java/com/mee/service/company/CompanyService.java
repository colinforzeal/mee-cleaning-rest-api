package com.mee.service.company;

import com.mee.entity.Company;
import com.mee.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    List<Order> getSchedule(String date, String idCompany);
    void save(Company company);
    Optional<Company> getByEmail(String email);
}
