package com.readingisgood.customerservice.proxy;

import com.readingisgood.customerservice.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="ORDER-SERVICE")
public interface OrderProxy {

    @GetMapping("/order/customerMonthlyOrders/{customerId}")
    List<OrderDto> findAllOrdersByCustomerId(@PathVariable("customerId") Long customerId,
                                                                    @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(defaultValue = "10") Integer pageSize);
}
