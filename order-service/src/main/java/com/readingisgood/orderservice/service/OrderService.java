package com.readingisgood.orderservice.service;

import com.readingisgood.orderservice.dto.BookDto;
import com.readingisgood.orderservice.dto.OrderDto;
import com.readingisgood.orderservice.entity.Order;
import com.readingisgood.orderservice.exception.OrderNotFoundException;
import com.readingisgood.orderservice.exception.StockNotFoundException;
import com.readingisgood.orderservice.proxy.BookProxy;
import com.readingisgood.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final BookProxy bookProxy;

    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {
        log.info("Calling book-service service for getting stock info of book");
        BookDto bookDto = bookProxy.getStockInfoById(orderDto.getBookId());
        if(bookDto.getStockCount() == 0){
            log.error("Sorry!! There is no available stock for this book");
            throw new StockNotFoundException("Sorry!! There is no available stock for this book");
        }
        log.info("Creating new order for book {}",bookDto.getName());
        orderDto.setDate(LocalDateTime.now());
        Order order = orderRepository.save(mapper.map(orderDto,Order.class));
        int newStockCount = bookDto.getStockCount() - 1;
        bookProxy.updateBookStock(bookDto.getId(), newStockCount);
        log.info("Book stock updated.New book stock is: {}",newStockCount);
        return mapper.map(order,OrderDto.class);
    }

    public OrderDto getOrderById(Long id) {
        log.info("Getting order info by order id : {}",id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with given id: " + id));
        return mapper.map(order,OrderDto.class);
    }

    public List<OrderDto> findOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        log.info("Listing paginated orders of customer : {}", customerId);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId, pageable);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> listOrdersByDate(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Listing orders between {} and {} ",startDate,endDate);
        List<Order> orderList = orderRepository.findAllByDateBetween(startDate,endDate);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> findAllOrdersByCustomerId(Long customerId) {
        log.info("Listing all orders of customer : {}",customerId);
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }
}
