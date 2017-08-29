package com.mee.service;

import com.mee.entity.Company;
import com.mee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(CompanyRepository repository) {
        this.companyRepository = repository;
    }

    public void save(Company company) {
        this.companyRepository.insert(company);
    }

    public Optional<Company> getByEmail(String email) {
        return this.companyRepository.findByEmail(email);
    }
}
