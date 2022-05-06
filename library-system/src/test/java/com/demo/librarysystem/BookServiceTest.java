package com.demo.librarysystem;

import com.demo.librarysystem.dao.BookRepository;
import com.demo.librarysystem.dto.BookDto;
import com.demo.librarysystem.entity.Book;
import com.demo.librarysystem.entity.Category;
import com.demo.librarysystem.service.BookService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {




    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Mock
    private Model model;

    @Test
    void findAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(
                Stream.of(
                        new Book("Time", "HG.Williams", 35, new Category("Science")),
                        new Book("Stars", "Brian Greene", 24, new Category("Astronomy"))
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
    void saveBookTest() {
        Book book = new Book("DBMS", "John", 45, new Category("Computers"));
        bookRepository.save(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void deleteBookTest() {
        int id = 1;
        bookRepository.deleteById(id);
        verify(bookRepository, times(1)).deleteById(id);
    }




}
