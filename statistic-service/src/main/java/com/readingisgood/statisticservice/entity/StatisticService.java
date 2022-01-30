package com.readingisgood.statisticservice.entity;

import com.readingisgood.statisticservice.dto.OrderDto;
import com.readingisgood.statisticservice.dto.StatisticDto;
import com.readingisgood.statisticservice.helper.StatisticConvertHelper;
import com.readingisgood.statisticservice.proxy.OrderProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticService {
    private final OrderProxy orderProxy;

    public List<StatisticDto> listMonthlyOrderStatistics(Long customerId){
        List<OrderDto> orderList = orderProxy.listMonthlyOrderStatistics(customerId);
        Map<String, List<OrderDto>> groupedOrderMap = orderList.stream().collect(Collectors.groupingBy(order -> order.getDate().getMonth().name()));
        ArrayList<StatisticDto> statisticDtos = StatisticConvertHelper.convertToStatisticDtoList(groupedOrderMap);
        return statisticDtos;
    }



}
