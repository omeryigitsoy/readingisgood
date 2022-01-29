package com.readingisgood.orderservice.exception;

import com.readingisgood.orderservice.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String message) {
        super(message,null,false,false);
    }
}
