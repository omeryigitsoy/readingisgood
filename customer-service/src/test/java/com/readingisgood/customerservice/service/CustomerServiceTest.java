package com.readingisgood.customerservice.service;


import com.readingisgood.customerservice.dto.CreateCustomerDto;
import com.readingisgood.customerservice.exception.CustomerNotFoundException;
import com.readingisgood.customerservice.proxy.OrderProxy;
import com.readingisgood.customerservice.repository.CustomerRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    ModelMapper mapper;
    @Mock
    OrderProxy orderProxy;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository,bCryptPasswordEncoder,mapper,orderProxy);
    }

    @Test
    public void createCustomer() {
        CreateCustomerDto customer = new CreateCustomerDto();
        customer.setFirstName("customer1");
        customer.setLastName("customerLastName");
        customer.setEmail("customer1@gmail.com");
        customer.setPassword("111111");
        when(customerService.createCustomer(customer)).thenReturn(customer);
        CreateCustomerDto createdCustomer = customerService.createCustomer(customer);
        assertEquals(createdCustomer.getEmail(), "customer1@gmail.com");

    }

    @Test
    void loadUserByUsername() {
        CreateCustomerDto customer = new CreateCustomerDto();
        customer.setFirstName("customer1");
        customer.setLastName("customerLastName");
        customer.setEmail("customer1@gmail.com");
        customer.setPassword("111111");
        try{
            customerService.loadUserByUsername(customer.getEmail());
        }catch (Exception e){
            exceptionRule.expect(CustomerNotFoundException.class);
        }
    }

    @Test
    void getCustomerDetailsByEmail() {
        CreateCustomerDto customer = new CreateCustomerDto();
        customer.setFirstName("customer1");
        customer.setLastName("customerLastName");
        customer.setEmail("customer1@gmail.com");
        customer.setPassword("111111");
        try{
            customerService.getCustomerDetailsByEmail(customer.getEmail());
        }catch (Exception e){
            exceptionRule.expect(CustomerNotFoundException.class);
        }
    }


}