package com.mee.repository;

import com.mee.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void save() throws Exception {
        Company company = new Company();
        company.setId("42");
        company.setPassword("1234567");
        companyRepository.save(company);
    }

    @Test
    public void findById() {
        Company company = companyRepository.findOne("42");
        System.out.println(company);
    }

    @Test
    public void deleteById() {
        companyRepository.delete("11");
    }

}