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
        company.setId("3");
        company.setPassword("qwerty12345");
        company.setEmail("viter.sneki@gmail.com");
        company.setAddress("Mexico, Charlie 18, 128-b");
        company.setName("FiveStarCleaning");
        company.setOpeningDays("Monday, Tuesday, Friday, Saturday, Sunday");
        company.setOpeningHours("10.30-23.30");
        company.setPhotoUrl("http://www.bugaga.ru/uploads/posts/2016-08/1471555196_subbota-25.jpg");
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