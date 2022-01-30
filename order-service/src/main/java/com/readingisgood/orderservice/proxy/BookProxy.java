package com.readingisgood.orderservice.proxy;

import com.readingisgood.orderservice.dto.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="BOOK-SERVICE")
public interface BookProxy {

    @GetMapping("/book/stock/{id}")
    BookDto getStockInfoById(@PathVariable("id") Long id);

    @PostMapping("/book/{id}/stock/{stockCount}")
    String updateBookStock(@PathVariable("id") Long id, @PathVariable("stockCount") Integer stockCount);
}
