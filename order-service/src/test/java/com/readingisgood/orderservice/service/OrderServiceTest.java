package com.readingisgood.orderservice.service;


import com.readingisgood.orderservice.dto.OrderDto;
import com.readingisgood.orderservice.entity.Order;
import com.readingisgood.orderservice.exception.OrderNotFoundException;
import com.readingisgood.orderservice.exception.StockNotFoundException;
import com.readingisgood.orderservice.proxy.BookProxy;
import com.readingisgood.orderservice.repository.OrderRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    ModelMapper mapper;
    @Mock
    BookProxy bookProxy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository, mapper,bookProxy);
    }

    @Test
    public void newOrder() {
        OrderDto order = new OrderDto();
        order.setId(1L);
        order.setDate(LocalDateTime.now());
        order.setAmount(new BigDecimal(1000));
        order.setBookId(1L);
        order.setCount(5);
        order.setCustomerId(1L);
        try{
            when(orderService.newOrder(order)).thenReturn(order);
        }catch (Exception e){
            exceptionRule.expect(StockNotFoundException.class);
        }
    }

    @Test
    public void getOrderById() {
        try{
            orderService.getOrderById(1L);
        }catch (Exception e){
            exceptionRule.expect(OrderNotFoundException.class);
        }
    }

    @Test
    public void findOrdersByCustomerId() {
        Order order = new Order();
        Order order2 = new Order();
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        List<OrderDto> collect = orderList.stream().map(order1 -> mapper.map(order1, OrderDto.class)).collect(Collectors.toList());
        when(orderService.findOrdersByCustomerId(1L,0,5)).thenReturn(collect);
        assertEquals(orderService.findOrdersByCustomerId(1L,0,5).size(), 2);
    }

    @Test
    public void listOrdersByDate() {
        Order order = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        orderList.add(order3);
        List<OrderDto> collect = orderList.stream().map(order1 -> mapper.map(order1, OrderDto.class)).collect(Collectors.toList());
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        when(orderService.listOrdersByDate(startDate,endDate)).thenReturn(collect);
        assertEquals(orderService.listOrdersByDate(startDate,endDate).size(), 3);
    }

    @Test
    public void findAllOrdersByCustomerId() {
        Order order = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        List<OrderDto> collect = orderList.stream().map(order1 -> mapper.map(order1, OrderDto.class)).collect(Collectors.toList());
        when(orderService.findAllOrdersByCustomerId(2L)).thenReturn(collect);
        assertEquals(orderService.findAllOrdersByCustomerId(2L).size(), 4);
    }
}