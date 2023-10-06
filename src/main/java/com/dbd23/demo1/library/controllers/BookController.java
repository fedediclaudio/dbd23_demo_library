package com.dbd23.demo1.library.controllers;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(path = "/{isbn}")
    public Book getBookById(@PathVariable String isbn) throws LibraryException {
        return this.bookService.findByIsbn(isbn).orElse(null);
    }
}
