package com.mee.controller;

import com.mee.entity.Company;
import com.mee.entity.Order;
import com.mee.repository.CompanyRepository;
import com.mee.security.auth.login.LoginAuthenticationProcessingFilter;
import com.mee.service.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.mee.utils.Utils.origin;

@CrossOrigin(origins = origin, maxAge = 3600)
@RestController
@RequestMapping(value = "/company")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;
    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @RequestMapping(value = "/getCalendar", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getCalendar(@RequestParam("date") String date,
                                                   @RequestParam("idCompany") String idCompany) {
        logger.info("Find orders for {} with idCompany {}", date, idCompany);
        List<Order> orderList = companyService.getSchedule(date.substring(0, 10), idCompany);//yyyy-mm-dd format
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)//todo: only admin function
    public ResponseEntity<List<Company>> findAllCompanies() {
        logger.info("Find all companies");
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)//todo: admin and company owner
    public ResponseEntity<Company> findCompanyById(@PathVariable String id) {
        logger.info("Find company with id = {}", id);
        return new ResponseEntity<>(companyRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveCompany(@Valid @RequestBody Company company) {
        logger.info("Save company: {}", company);
        companyRepository.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)//todo: only company (or together with admin)
    public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company) {
        logger.info("Update company with id = {}", company.getId());
        companyRepository.save(company);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)//todo: only company (or together with admin)
    public ResponseEntity<?> deleteCompany(@PathVariable String id) {
        logger.info("Delete company with id = {}", id);
        companyRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
