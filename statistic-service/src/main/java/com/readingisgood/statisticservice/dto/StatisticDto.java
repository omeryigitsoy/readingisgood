package com.readingisgood.statisticservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatisticDto {
    public String month;
    public Integer totalOrderCount;
    public Integer totalBookCount;
    public BigDecimal totalPurchasedAmount;
}
