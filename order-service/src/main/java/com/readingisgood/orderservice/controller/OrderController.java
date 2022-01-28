package com.readingisgood.orderservice.controller;

import com.readingisgood.orderservice.dto.OrderDto;
import com.readingisgood.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> newOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity(orderService.newOrder(orderDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id){
        return new ResponseEntity<>(orderService.getOrderById(id),HttpStatus.OK);
    }

    @GetMapping("/customerOrders/{customerId}")
    public ResponseEntity<List<OrderDto>> findOrdersByCustomerId(@PathVariable("customerId") Long customerId,
                                                                 @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                 @RequestParam(defaultValue = "10") Integer pageSize){

        return new ResponseEntity<>(orderService.findOrdersByCustomerId(customerId,pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/listOrdersByDate")
    public ResponseEntity<List<OrderDto>> listOrdersByDate(@RequestParam Date startDate,@RequestParam Date endDate){
        return new ResponseEntity<>(orderService.listOrdersByDate(startDate,endDate),HttpStatus.OK);
    }

}
