package com.mee.service.company;

import com.mee.entity.Company;
import com.mee.entity.Order;
import com.mee.repository.CompanyRepository;
import com.mee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public List<Order> getSchedule(String date, String idCompany) {
        try {
            return orderRepository.findByWorkDayAndCompanyId(new SimpleDateFormat("yyyy-MM-dd").parse(date), idCompany);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Company company) {
        this.companyRepository.insert(company);
    }

    public Optional<Company> getByEmail(String email) {
        return this.companyRepository.findByEmail(email);
    }
}
