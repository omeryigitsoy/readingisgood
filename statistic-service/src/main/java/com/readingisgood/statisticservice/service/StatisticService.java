package com.readingisgood.statisticservice.service;

import com.readingisgood.statisticservice.dto.OrderDto;
import com.readingisgood.statisticservice.dto.StatisticDto;
import com.readingisgood.statisticservice.helper.StatisticConvertHelper;
import com.readingisgood.statisticservice.proxy.OrderProxy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticService {
    private final OrderProxy orderProxy;

    public List<StatisticDto> listMonthlyOrderStatistics(Long customerId){
        log.info("Listing all monthly order statistics of customer : {}",customerId);
        log.info("Calling order-service service for getting order info of customer");
        List<OrderDto> orderList = orderProxy.listMonthlyOrderStatistics(customerId);
        Map<String, List<OrderDto>> groupedOrderMap = orderList.stream().collect(Collectors.groupingBy(order -> order.getDate().getMonth().name()));
        ArrayList<StatisticDto> statisticDtos = StatisticConvertHelper.convertToStatisticDtoList(groupedOrderMap);
        return statisticDtos;
    }



}
