package com.mee.controller;

import com.mee.entity.Company;
import com.mee.entity.Order;
import com.mee.repository.CompanyRepository;
import com.mee.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/getCalendar", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getCalendar(@RequestParam("date") String date,
                                                   @RequestParam("idCompany") String idCompany) {
        List<Order> orderList = companyService.getSchedule(date.substring(0, 10), idCompany);//yyyy-mm-dd format
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> findAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Company> findCompanyById(@PathVariable String id) {
        return new ResponseEntity<>(companyRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveCompany(@Valid @RequestBody Company company) {//todo: don't work
        System.out.println(company);
        Company savedEntity = companyRepository.save(company);
        System.out.println(savedEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company) {//todo: don't work
        System.out.println(company);
        companyRepository.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCompany(@PathVariable String id) {
        System.out.println(id);
        companyRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
