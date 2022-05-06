package com.demo.librarysystem.controller;


import com.demo.librarysystem.dto.CategoryDto;
import com.demo.librarysystem.entity.Category;
import com.demo.librarysystem.service.BookService;
import com.demo.librarysystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@EnableWebSecurity
@Controller
public class CategoryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

   private static final String ADD_CATEGORY_FORM ="add-category-form";

    @GetMapping("/listCategories")
    public String listCategories(Model model)
    {
        List<CategoryDto> categories=categoryService.findAll();
        model.addAttribute("categories",categories);
        return "list-categories";
    }

    @GetMapping("/addCategoryForm")
    public String addCategoryForm(Model model)
    {
        CategoryDto categoryDto = new CategoryDto();
        model.addAttribute("category",categoryDto);
        return ADD_CATEGORY_FORM;
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ADD_CATEGORY_FORM;
        }
        List<CategoryDto> categories = categoryService.findAll();
        for (CategoryDto tempCategory: categories) {
            if (tempCategory.getName().equals(category.getName()))
            {
                return "error-already-added";
            }
        }
        Category category1 = new Category(category.getName());
        category1.setId(category.getId());
        categoryService.save(category1);

        return "redirect:/listCategories";
    }

    @GetMapping("/showFormForUpdateCategory")
    public String showFormForUpdateCategory(@RequestParam("categoryId") int id, Model model)
    {
        CategoryDto category=categoryService.findById(id);
        model.addAttribute("category",category);
        return ADD_CATEGORY_FORM;
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("categoryId") int id)
    {
        categoryService.deleteById(id);
        return "redirect:/listCategories";
    }
}
