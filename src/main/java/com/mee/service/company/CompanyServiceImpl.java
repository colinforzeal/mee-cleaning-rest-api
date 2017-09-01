package com.mee.service.company;

import com.mee.entity.Company;
import com.mee.entity.Order;
import com.mee.repository.CompanyRepository;
import com.mee.repository.OrderRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer CAPACITY = 9;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Company> getAllCompanies() {
        Pageable pageable = new PageRequest(DEFAULT_PAGE, CAPACITY);
        return companyRepository.findAll(pageable).getContent();
    }

    public List<Order> getSchedule(String date, String idCompany) {
        String pattern = "yyyy-MM-dd";
        DateTime now = DateTime.parse(date.substring(0, 10), DateTimeFormat.forPattern(pattern));

        DateTime weekStart = now.withDayOfWeek(DateTimeConstants.MONDAY).withTimeAtStartOfDay();
        DateTime weekEnd = now.withDayOfWeek(DateTimeConstants.SUNDAY).plusDays(1).withTimeAtStartOfDay();

        String from = weekStart.getYear() + "-" + weekStart.getMonthOfYear() + "-" + weekStart.getDayOfMonth();
        String to = weekEnd.getYear() + "-" + weekEnd.getMonthOfYear() + "-" + weekEnd.getDayOfMonth();

        try {
            return orderRepository.findByWorkDayAndCompanyId(new SimpleDateFormat(pattern).parse(from),
                    new SimpleDateFormat(pattern).parse(to), idCompany);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getByName(String name) {
        return this.companyRepository.findByNameIsLike(name);
    }

    public Optional<Company> getById(String id) {
        return this.companyRepository.findById(id);
    }

    public Company save(Company company) {
        return this.companyRepository.insert(company);
    }

    public Company update(Company company) {
        return this.companyRepository.save(company);
    }

    public void delete(String id) {
        this.companyRepository.delete(id);
    }

    public Optional<Company> getByEmail(String email) {
        return this.companyRepository.findByEmail(email);
    }
}
