package com.demo.librarysystem.dto;

import com.demo.librarysystem.entity.Book;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data@AllArgsConstructor@NoArgsConstructor
public class CategoryDto {

    private int id;

    @NotNull(message = "is required")
    @Size(min = 2,message = "please specify at least two characters")
    private String name;

    private List<Book> books;

    public CategoryDto(String name)
    {
        this.name = name;
    }
}
