package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public Author createAuthor(String fullname, LocalDate dateOfBirth) throws LibraryException;

    public Author updateAuthor(ObjectId id, String fullname, LocalDate dateOfBirth) throws LibraryException;

    Author updateAuthor(Author author) throws LibraryException;

    public boolean deleteAuthor(ObjectId id) throws LibraryException;

    public Optional<Author> getById(ObjectId id);

    public List<Author> getListOfAuthors();

    public List<Author> getYoungAuthors();

    public Author getAuthorWithMoreBooks();
}
