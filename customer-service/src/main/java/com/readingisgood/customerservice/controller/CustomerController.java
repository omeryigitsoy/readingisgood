package com.readingisgood.customerservice.controller;

import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.dto.OrderDto;
import com.readingisgood.customerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CreateCustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto){
        return new ResponseEntity(customerService.createCustomer(createCustomerDto),HttpStatus.CREATED);
    }

    @GetMapping("/customerOrders/{customerId}")
    public ResponseEntity<List<OrderDto>> findAllOrdersByCustomerId(@PathVariable("customerId") Long customerId,
                                                                    @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(defaultValue = "10") Integer pageSize){
        return new ResponseEntity<>(customerService.findAllOrdersByCustomerId(customerId,pageNumber,pageSize),HttpStatus.OK);
    }

}
