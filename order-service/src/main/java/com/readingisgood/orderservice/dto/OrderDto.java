package com.readingisgood.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private Long bookId;
    private BigDecimal amount;
    private Integer count;
    private Date date;
}
