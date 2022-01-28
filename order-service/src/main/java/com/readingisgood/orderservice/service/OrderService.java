package com.readingisgood.orderservice.service;

import com.readingisgood.orderservice.dto.OrderDto;
import com.readingisgood.orderservice.entity.Order;
import com.readingisgood.orderservice.exception.OrderNotFoundException;
import com.readingisgood.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    public OrderDto newOrder(OrderDto orderDto) {
        Order order = orderRepository.save(mapper.map(orderDto,Order.class));
        return mapper.map(order,OrderDto.class);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with given id" + id));
        return mapper.map(order,OrderDto.class);
    }

    public List<OrderDto> findOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId, pageable);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> listOrdersByDate(Date startDate, Date endDate) {
        List<Order> orderList = orderRepository.findAllByDateBetween(startDate,endDate);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }
}
