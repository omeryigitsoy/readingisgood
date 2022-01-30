package com.readingisgood.statisticservice.helper;

import com.readingisgood.statisticservice.dto.OrderDto;
import com.readingisgood.statisticservice.dto.StatisticDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticConvertHelper {
    public static ArrayList<StatisticDto> convertToStatisticDtoList(Map<String, List<OrderDto>> groupedOrderMap) {
        ArrayList<StatisticDto> statisticDtos = new ArrayList<>();
        groupedOrderMap.keySet().forEach(key->{
            StatisticDto statisticDto = new StatisticDto();
            List<OrderDto> orderDtos = groupedOrderMap.get(key);
            int totalBookCount = orderDtos.stream().mapToInt(orderDto-> orderDto.getCount()).sum();
            Double totalPurchasedAmount = orderDtos.stream().mapToDouble(orderDto -> orderDto.getAmount().doubleValue()).sum();
            statisticDto.setTotalOrderCount(orderDtos.size());
            statisticDto.setTotalBookCount(totalBookCount);
            statisticDto.setTotalPurchasedAmount(new BigDecimal(totalPurchasedAmount));
            statisticDto.setMonth(key);
            statisticDtos.add(statisticDto);
        });
        return statisticDtos;
    }
}
