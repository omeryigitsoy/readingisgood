package com.readingisgood.orderservice.service;

import com.readingisgood.orderservice.dto.BookDto;
import com.readingisgood.orderservice.dto.OrderDto;
import com.readingisgood.orderservice.entity.Order;
import com.readingisgood.orderservice.exception.OrderNotFoundException;
import com.readingisgood.orderservice.exception.StockNotFoundException;
import com.readingisgood.orderservice.proxy.BookProxy;
import com.readingisgood.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final BookProxy bookProxy;

    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {
        //Integer stockCount = bookProxy.getStockCountById(orderDto.getBookId());
        BookDto bookDto = bookProxy.getStockInfoById(orderDto.getBookId());
        if(bookDto.getStockCount() == 0){
            throw new StockNotFoundException("Sorry!! There is no available stock for this book");
        }
        orderDto.setDate(LocalDateTime.now());
        Order order = orderRepository.save(mapper.map(orderDto,Order.class));
        String updateBookStock = bookProxy.updateBookStock(bookDto.getId(), bookDto.getStockCount() - 1);
        return mapper.map(order,OrderDto.class);
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with given id: " + id));
        return mapper.map(order,OrderDto.class);
    }

    public List<OrderDto> findOrdersByCustomerId(Long customerId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId, pageable);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> listOrdersByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orderList = orderRepository.findAllByDateBetween(startDate,endDate);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }

    public List<OrderDto> findAllOrdersByCustomerId(Long customerId) {
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId);
        return orderList.stream().map(order -> mapper.map(order,OrderDto.class)).collect(Collectors.toList());
    }
}
