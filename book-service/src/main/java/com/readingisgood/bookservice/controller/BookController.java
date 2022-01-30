package com.readingisgood.bookservice.controller;

import com.readingisgood.bookservice.dto.BookDto;
import com.readingisgood.bookservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Repository
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto createBookDto){
        return new ResponseEntity(bookService.addBook(createBookDto), HttpStatus.CREATED);

    }

    @PostMapping("/{id}/stock/{stockCount}")
    public ResponseEntity<String> updateBookStock(@PathVariable("id") Long id,@PathVariable("stockCount") Integer stockCount){
        bookService.updateBookStock(id,stockCount);
        return ResponseEntity.ok("Stock count updated");
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> listAllBooks(){
        return ResponseEntity.ok(bookService.listAllBooks());
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<BookDto> getStockInfoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookService.getStockInfoById(id));
    }
}
