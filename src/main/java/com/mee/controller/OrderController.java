package com.mee.controller;

import com.mee.entity.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @RequestMapping(value = "/getByIdUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getHistoryByIdUser(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getByIdCompany/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> findAllCompanies(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
