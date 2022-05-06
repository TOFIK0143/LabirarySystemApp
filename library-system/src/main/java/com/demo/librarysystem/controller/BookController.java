package com.demo.librarysystem.controller;


import com.demo.librarysystem.dto.BookDto;
import com.demo.librarysystem.dto.CategoryDto;
import com.demo.librarysystem.entity.Book;
import com.demo.librarysystem.service.BookService;
import com.demo.librarysystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;





    @GetMapping("/listBooks")
    public String listBooks(@RequestParam("categoryId") Optional<Integer> categoryId, Model model){
        List<Book> books;

        List<BookDto> books1;

        if(categoryId.isEmpty())
        {
             books1=bookService.findAll();
            model.addAttribute("books",books1);
        }
        else
        {
             books = bookService.findBookByCategory(categoryId.get().intValue());
            model.addAttribute("books",books);

        }

        List<CategoryDto> categories=categoryService.findAll();
        model.addAttribute("categories",categories);


        return "list-books";
    }

    @GetMapping("/addBookForm")
    public String addBook(Model model)
    {
        BookDto bookDto = new BookDto();
        model.addAttribute("book",bookDto);

        List<CategoryDto> categories=categoryService.findAll();
        model.addAttribute( "categories",categories);
        return "add-book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@Valid @ModelAttribute("book") Book bookDto, BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("book",bookDto);
            List<CategoryDto> categories=categoryService.findAll();
            model.addAttribute("categories",categories);
            return "add-book-form";
        }
        Book book= new Book(bookDto.getName(),bookDto.getAuthor(),bookDto.getQuantity(),bookDto.getCategory());
        book.setId(bookDto.getId());
        bookService.save(book);

        return "redirect:/listBooks";
    }

    @GetMapping("showFormForUpdateBook")
    public String showFormForUpdateBook(@RequestParam("bookId") int id, Model model)
    {
        BookDto book=bookService.findById(id);
        model.addAttribute("book",book);
        List<CategoryDto> categories=categoryService.findAll();
        model.addAttribute("categories",categories);

        return "add-book-form";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") int id)
    {
        bookService.deleteById(id);
        return "redirect:/listBooks";
    }

    @GetMapping("/searchBook")
    public String searchBook() {

    /*    List<Book> books;
        List<BookDto> books1;
        books1 = bookService.findById();
        model.addAttribute("books", books1);

     */
        return "search";
    }
}
