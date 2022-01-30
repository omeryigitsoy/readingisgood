package com.readingisgood.statisticservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long bookId;
    private BigDecimal amount;
    private Integer count;
    private LocalDateTime date;
}
