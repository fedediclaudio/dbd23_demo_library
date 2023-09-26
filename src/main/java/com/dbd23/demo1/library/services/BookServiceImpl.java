package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.model.DigitalBook;
import com.dbd23.demo1.library.model.PhysicalBook;
import com.dbd23.demo1.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public PhysicalBook createPhysicalBook(String isbn, String title, int year, String comments, float weight, String location, Author author) throws LibraryException {
        PhysicalBook physicalBook = new PhysicalBook(isbn, title, year, comments, weight, location, author);
        author.getBooks().add(physicalBook);
        this.bookRepository.save(physicalBook);
        return physicalBook;
    }

    @Override
    @Transactional
    public DigitalBook createDigitalBook(String isbn, String title, int year, String comments, String format, float size, Author author) throws LibraryException {
        DigitalBook digitalBook = new DigitalBook(isbn, title, year, comments, format, size, author);
        author.getBooks().add(digitalBook);
        this.bookRepository.save(digitalBook);
        return digitalBook;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DigitalBook> findAllDigitalBook() {
        return this.bookRepository.findAllDigitalBook();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByIsbn(String isbn) {
        return this.bookRepository.findByIsbn(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByAuthor(Author author) {
        return this.bookRepository.findByAuthor(author.getId());
    }
}
