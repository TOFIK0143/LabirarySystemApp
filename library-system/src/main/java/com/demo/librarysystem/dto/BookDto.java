package com.demo.librarysystem.dto;

import com.demo.librarysystem.entity.Category;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class BookDto {

    private int id;

    @NotNull(message = "is required")
    @Size(min=2,message = "please specify at least two characters")
    private String name;

    @NotNull(message = "is required")
    @Size(min=2,message = "please specify at least two characters")
    private String author;

    @Min(value = 1,message = "a minimum of 1 quantity is required")
    private int quantity;

    private Category category;

    public BookDto(String name, String author, int quantity, Category category) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.category = category;
    }
}
