package com.dbd23.demo1.library.repositories;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.*;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    public void save(Author author) throws LibraryException;

    public void update(Author author) throws LibraryException;

    public boolean delete(Author author) throws LibraryException;

    public Optional<Author> findById(Long id);

    public List<Author> findAll();

    public List<Author> findByYoung();

    public Optional<Author> findWithMoreBooks();
}
