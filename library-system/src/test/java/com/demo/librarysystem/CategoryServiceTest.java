package com.demo.librarysystem;

import com.demo.librarysystem.dao.BookRepository;
import com.demo.librarysystem.dao.CategoryRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

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


    @Test
    void findAllCategoriesTest() {
        when(categoryRepository.findAll()).thenReturn(
                Stream.of(
                                new Category("Science"),
                                new Category("Astronomy")
                        )
                        .collect(Collectors.toList()));

        Assertions.assertEquals(2, categoryRepository.findAll().size());

    }

    @Test
    void findByIdCategoryTest() {
        int id = 1;
        when(categoryRepository.findById(id)).thenReturn(
                Optional.of(new Category("Computers"))
        );

        Assertions.assertEquals("Computers", categoryService.findById(id).getName());
    }

    @Test
    void saveCategoryTest() {
        Category category = new Category("Computers");
        categoryRepository.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void deleteCategoryTest() {
        int id = 1;
        categoryRepository.deleteById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }

}
