package com.demo.librarysystem.service;

import com.demo.librarysystem.dto.BookDto;
import com.demo.librarysystem.entity.Book;

import java.util.List;

public interface BookService {

     List<BookDto> findAll();

     BookDto findById(int id);


     void save(Book book);

     void deleteById(int id);

     List<Book> findBookByCategory(int categoryId);

}
