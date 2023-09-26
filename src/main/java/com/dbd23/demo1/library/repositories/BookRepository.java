package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.model.DigitalBook;
import com.dbd23.demo1.library.model.PhysicalBook;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    public void save(Book book) throws LibraryException;

    List<Book> findAll();

    List<DigitalBook> findAllDigitalBook();

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor(Long id);
}
