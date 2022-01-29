package com.readingisgood.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
}
