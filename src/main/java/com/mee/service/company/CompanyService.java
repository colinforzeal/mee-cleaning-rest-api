package com.mee.service.company;

import com.mee.entity.Company;
import com.mee.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    List<Order> getSchedule(String date, String idCompany);
    List<Company> getByName(String name, String page);
    Optional<Company> getById(String id);
    Company save(Company company);
    Company update(Company company);
    void delete(String id);
    Optional<Company> getByEmail(String email);
}
