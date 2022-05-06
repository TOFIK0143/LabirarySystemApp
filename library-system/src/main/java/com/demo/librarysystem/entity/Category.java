package com.demo.librarysystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotNull(message = "is required")
    @Size(min = 2,message = "please specify at least two characters")
    @Column(name = "name")
    private String name;

    //if a category is deleted, all the corresponding books must be deleted
    //mapped by=category. category refers to the property in Book class
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Book> books;


    public Category(String name) {
        this.name = name;
    }
}
