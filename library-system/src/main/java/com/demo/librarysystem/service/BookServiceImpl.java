package com.demo.librarysystem.service;

import com.demo.librarysystem.dao.BookRepository;
import com.demo.librarysystem.custom.exceptions.BookNotFoundException;
import com.demo.librarysystem.dto.BookDto;
import com.demo.librarysystem.dto.CategoryDto;
import com.demo.librarysystem.entity.Book;
import com.demo.librarysystem.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    //inject BookRepository
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper myMapper;


    @Override
    public List<BookDto> findAll() {
        return (List<BookDto>) bookRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(int id) {
        Optional<Book> result = bookRepository.findById(id);
        BookDto book;
        if(result.isPresent())
        {
            book= convertToDTO(result.get());
        }
        else
        {
            throw new BookNotFoundException("Did not find the book!");
        }
        return book;
    }

    @Override
    public void save(Book book) {

     // Book book1 = convertToEntity(book);
      bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
      bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findBookByCategory(int categoryId) {

        List<Book> booksByCategory = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {

            if(book.getCategory().getId()==categoryId)
            {
                booksByCategory.add(book);
            }

        }
        return booksByCategory;
    }

    private BookDto convertToDTO(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setCategory(new Category());
        myMapper.map(book, bookDto);
        return bookDto;
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setCategory(new Category());
        myMapper.map(bookDto, book);
        return book;
    }


}
