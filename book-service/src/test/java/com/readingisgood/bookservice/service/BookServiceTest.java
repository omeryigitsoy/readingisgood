package com.readingisgood.bookservice.service;

import com.readingisgood.bookservice.dto.BookDto;
import com.readingisgood.bookservice.entity.Book;
import com.readingisgood.bookservice.exception.BookNotFoundException;
import com.readingisgood.bookservice.repository.BookRepository;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

class BookServiceTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    BookService bookService;

    @Mock
    BookRepository bookRepository;
    @Mock
    ModelMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService(bookRepository, mapper);
    }

    @Test
    void addBook() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setStockCount(10);
        book.setPrice(new BigDecimal(50));
        when(bookService.addBook(book)).thenReturn(book);
        BookDto bookDto = bookService.addBook(book);
        assertEquals(Long.valueOf(bookDto.getId()), Long.valueOf(1L));

    }

    @Test
    void updateBookStock() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setStockCount(50);
        book.setPrice(new BigDecimal(50));
        try{
            bookService.updateBookStock(book.getId(),100);
        }catch (Exception e){
            exceptionRule.expect(BookNotFoundException.class);
        }
    }

    @Test
    void listAllBooks() {
        Book book = new Book();
        ArrayList<Book> bookData = new ArrayList<>();
        bookData.add(book);
        List<BookDto> collect = bookData.stream().map(book1 -> mapper.map(book1, BookDto.class)).collect(Collectors.toList());
        when(bookService.listAllBooks()).thenReturn(collect);
        assertEquals(bookService.listAllBooks().size(), 1);

    }

    @Test
    void getStockInfoById() {
        BookDto book = new BookDto();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setStockCount(50);
        book.setPrice(new BigDecimal(50));
        try{
            when(bookService.getStockInfoById(book.getId())).thenReturn(book);
        }catch (Exception e){
            exceptionRule.expect(BookNotFoundException.class);
        }
    }
}