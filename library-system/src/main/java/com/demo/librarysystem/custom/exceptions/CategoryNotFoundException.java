package com.demo.librarysystem.custom.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public  CategoryNotFoundException(String message) {
        super(message);
    }
}
