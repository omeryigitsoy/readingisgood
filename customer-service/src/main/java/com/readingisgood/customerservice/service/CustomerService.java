package com.readingisgood.customerservice.service;

import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.entity.Customer;
import com.readingisgood.customerservice.exception.CustomerNotFoundException;
import com.readingisgood.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper mapper;

    public CreateCustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
        createCustomerDto.setPassword(bCryptPasswordEncoder.encode(createCustomerDto.getPassword()));
        Customer savedCustomer = customerRepository.save(mapper.map(createCustomerDto,Customer.class));
        return mapper.map(savedCustomer, CreateCustomerDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username);
        if(customer == null){
            throw new CustomerNotFoundException(username);
        }
        return new User(customer.getEmail(),customer.getPassword(),true,true,true,true,new ArrayList<>());
    }

    public CreateCustomerDto getCustomerDetailsByEmail(String email){
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null){
            throw new CustomerNotFoundException(email);
        }
        return mapper.map(customer,CreateCustomerDto.class);

    }
}
