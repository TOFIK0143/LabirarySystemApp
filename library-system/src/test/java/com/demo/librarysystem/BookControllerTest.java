package com.demo.librarysystem;

import com.demo.librarysystem.controller.CategoryController;
import com.demo.librarysystem.dao.BookRepository;
import com.demo.librarysystem.dao.CategoryRepository;
import com.demo.librarysystem.dto.BookDto;
import com.demo.librarysystem.entity.Book;
import com.demo.librarysystem.entity.Category;
import com.demo.librarysystem.service.BookService;
import com.demo.librarysystem.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookControllerTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Mock
    private Model model;

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    public BookControllerTest() {
    }

    //------------------------------TESTING BOOK CONTROLLER

    @Test
    void listBooksControllerTest() {
        when(bookService.findAll()).thenReturn(
                Stream.of(
                        new BookDto("Time", "HG.Williams", 35, new Category("Science")),
                        new BookDto("Stars", "Brian Greene", 24, new Category("Astronomy"))
                ).collect(Collectors.toList()));

        Assertions.assertEquals(2, bookRepository.findAll().size());

    }

    @Test
    void findByIdBookTest() {
        int id = 1;
        when(bookRepository.findById(id)).thenReturn(
                Optional.of(new Book("DBMS", "John", 45, new Category("Computers")))
        );

        Assertions.assertEquals("DBMS", bookService.findById(id).getName());
    }

    @Test
    void saveBookControllerTest() {
        Book book = new Book("DBMS", "John", 45, new Category("Computers"));
        bookService.save(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void deleteBookControllerTest() {
        int id = 1;
        bookService.deleteById(id);
        verify(bookRepository, times(1)).deleteById(id);
    }
}
