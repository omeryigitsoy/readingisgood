package com.readingisgood.bookservice.service;

import com.readingisgood.bookservice.dto.BookDto;
import com.readingisgood.bookservice.entity.Book;
import com.readingisgood.bookservice.exception.BookNotFoundException;
import com.readingisgood.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;

    public BookDto addBook(BookDto createBookDto) {
        Book newBook = bookRepository.save(mapper.map(createBookDto,Book.class));
        return mapper.map(newBook, BookDto.class);
    }

    public void updateBookStock(Long id, Integer stockCount) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        book.setStockCount(stockCount);
        bookRepository.save(book);
    }

    public List<BookDto> listAllBooks(){
        return bookRepository.findAll().stream().map(book -> mapper.map(book,BookDto.class)).collect(Collectors.toList());
    }

    public Integer getStockCountById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return book.getStockCount();
    }
    public BookDto getStockInfoById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return mapper.map(book,BookDto.class);
    }
}
