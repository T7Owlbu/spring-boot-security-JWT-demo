package com.example.demospringsecurity.controller;


import com.example.demospringsecurity.entity.Customer;
import com.example.demospringsecurity.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author T7Owlbu
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/{username}")
    public ResponseEntity<Customer> getByUsername(@PathVariable("username") String username){
        Customer customer = customerService.getCustomerByUsername(username);
        return ResponseEntity.ok(customer);
    }

}

