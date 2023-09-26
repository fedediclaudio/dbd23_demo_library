package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author createAuthor(String fullname, LocalDate dateOfBirth) throws LibraryException {
        Author author = new Author(fullname, dateOfBirth);
        this.authorRepository.save(author);
        return author;
    }

    @Override
    @Transactional
    public Author updateAuthor(Long id, String fullname, LocalDate dateOfBirth) throws LibraryException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setFullname(fullname);
            author.setDateOfBirth(dateOfBirth);
            this.authorRepository.update(author);
            return author;
        } else return null;
    }

    @Override
    @Transactional
    public boolean deleteAuthor(Long id) throws LibraryException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            this.authorRepository.delete(author);
            return true;
        } else return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getListOfAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getYoungAuthors() {
        return this.authorRepository.findByYoung();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorWithMoreBooks() {
        Optional<Author> author =  this.authorRepository.findWithMoreBooks();
        if(author.isPresent()) {
            Author a = author.get();
            a.getBooks().size();
            return a;
        }
        return null;
    }
}
