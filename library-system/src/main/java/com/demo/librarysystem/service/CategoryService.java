package com.demo.librarysystem.service;

import com.demo.librarysystem.dto.CategoryDto;
import com.demo.librarysystem.entity.Category;

import java.util.List;

public interface CategoryService {

     List<CategoryDto> findAll();

     CategoryDto findById(int id);

     void save(Category category);

     void deleteById(int id);


}
