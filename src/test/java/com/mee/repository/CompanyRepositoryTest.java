package com.mee.repository;

import com.mee.entity.Company;
import com.mee.entity.OpeningHours;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Arrays.asList;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void save() throws Exception {
        Company company = new Company();
        company.setId("4");
        company.setPassword("new_password");
        company.setEmail("blank.irekis@gmail.com");
        company.setAddress("USA, New York, Charlie 18, 128-b");
        company.setName("NewCleanAgency");
        company.setOpeningDays(asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        company.setOpeningHours(new OpeningHours(LocalTime.now(), LocalTime.now().plusHours(8)));
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