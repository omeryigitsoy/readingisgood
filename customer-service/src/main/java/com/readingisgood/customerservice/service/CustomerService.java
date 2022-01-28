package com.readingisgood.customerservice.service;

import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.entity.Customer;
import com.readingisgood.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public CreateCustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer savedCustomer = customerRepository.save(mapper.map(createCustomerDto,Customer.class));
        return mapper.map(savedCustomer, CreateCustomerDto.class);
    }
}
