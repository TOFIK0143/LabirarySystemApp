package com.demo.librarysystem.service;

import com.demo.librarysystem.dao.CategoryRepository;
import com.demo.librarysystem.custom.exceptions.CategoryNotFoundException;
import com.demo.librarysystem.dto.CategoryDto;
import com.demo.librarysystem.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    //inject category repository

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper myMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(int id) {

        Optional<Category>result=categoryRepository.findById(id);
        CategoryDto category;
        if(result.isPresent())
        {
            category = convertToDTO(result.get());
        }
        else
        {
            throw new CategoryNotFoundException("Did not find the category!");
        }
        return category;
    }

    @Override
    public void save(Category category) {

        categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
       categoryRepository.deleteById(id);
    }


    private CategoryDto convertToDTO(Category category) {
        return myMapper.map(category, CategoryDto.class);
    }

}
