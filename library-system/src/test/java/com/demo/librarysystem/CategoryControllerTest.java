package com.demo.librarysystem;

import com.demo.librarysystem.controller.CategoryController;
import com.demo.librarysystem.dao.BookRepository;
import com.demo.librarysystem.dao.CategoryRepository;
import com.demo.librarysystem.dto.CategoryDto;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {

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
    void ListAllCategoriesControllerTest() {
        when(categoryService.findAll()).thenReturn(
                Stream.of(
                                new CategoryDto("Science"),
                                new CategoryDto("Astronomy")
                        )
                        .collect(Collectors.toList()));

        Assertions.assertEquals(2, categoryRepository.findAll().size());

    }

    @Test
    void saveCategoryControllerTest() {
        Category category = new Category("Computers");
        categoryService.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void deleteCategoryControllerTest() {
        int id = 1;
        categoryService.deleteById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }



    @Test
    void addCategoryFormTest()
    {
        CategoryController categoryController = new CategoryController();
        String response=categoryController.addCategoryForm(model);
        Assertions.assertEquals("add-category-form",response);
    }


}
