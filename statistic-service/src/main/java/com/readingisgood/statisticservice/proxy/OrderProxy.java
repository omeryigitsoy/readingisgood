package com.readingisgood.statisticservice.proxy;

import com.readingisgood.statisticservice.dto.OrderDto;
import com.readingisgood.statisticservice.dto.StatisticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="ORDER-SERVICE")
public interface OrderProxy {

    @GetMapping("/order/listMonthlyOrderStatistics/{customerId}")
    List<OrderDto> listMonthlyOrderStatistics(@PathVariable("customerId") Long customerId);
}
