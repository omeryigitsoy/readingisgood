package com.readingisgood.bookservice.service;

import com.readingisgood.bookservice.dto.BookDto;
import com.readingisgood.bookservice.entity.Book;
import com.readingisgood.bookservice.exception.BookNotFoundException;
import com.readingisgood.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    @Transactional
    public BookDto addBook(BookDto createBookDto) {
        Book newBook = bookRepository.save(mapper.map(createBookDto,Book.class));
        log.info("{} number of {} book(s) added",createBookDto.getStockCount(),createBookDto.getName());
        return mapper.map(newBook, BookDto.class);
    }

    public void updateBookStock(Long id, Integer stockCount) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        book.setStockCount(stockCount);
        bookRepository.save(book);
        log.info("Stock count updated to {} for book name {}",stockCount,book.getName());
    }

    public List<BookDto> listAllBooks(){
        log.info("Listing all books");
        return bookRepository.findAll().stream().map(book -> mapper.map(book,BookDto.class)).collect(Collectors.toList());
    }

    public BookDto getStockInfoById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        log.info("Getting stock info of {} by id: ",book.getName(),id);
        return mapper.map(book,BookDto.class);
    }
}
