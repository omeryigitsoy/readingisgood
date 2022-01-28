package com.readingisgood.customerservice.controller;

import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CreateCustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto){
        return new ResponseEntity(customerService.createCustomer(createCustomerDto),HttpStatus.CREATED);
    }

}
