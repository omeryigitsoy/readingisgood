package com.readingisgood.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotNull(message = "Book name cannot be empty")
    private String name;
    @NotNull(message = "Author info cannot be empty")
    private String author;
    private Integer stockCount;
    private BigDecimal price;
}
