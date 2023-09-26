package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.model.DigitalBook;
import com.dbd23.demo1.library.model.PhysicalBook;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public PhysicalBook createPhysicalBook(String isbn, String title, int year, String comments, float weight, String location, Author author) throws LibraryException;

    public DigitalBook createDigitalBook(String isbn, String title, int year, String comments, String format, float size, Author author) throws LibraryException;

    public List<Book> findAll();

    public List<DigitalBook> findAllDigitalBook();

    public Optional<Book> findByIsbn(String isbn);

    public List<Book> findByAuthor(Author author);
}
