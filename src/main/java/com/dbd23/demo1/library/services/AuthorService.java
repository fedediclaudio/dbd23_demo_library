package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public Author createAuthor(String fullname, LocalDate dateOfBirth) throws LibraryException;

    public Author updateAuthor(Long id, String fullname, LocalDate dateOfBirth) throws LibraryException;

    public boolean deleteAuthor(Long id) throws LibraryException;

    public Optional<Author> getById(Long id);

    public List<Author> getListOfAuthors();

    public List<Author> getYoungAuthors();

    public Author getAuthorWithMoreBooks();
}
