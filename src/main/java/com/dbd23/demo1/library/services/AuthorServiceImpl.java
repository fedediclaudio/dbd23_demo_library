package com.dbd23.demo1.library.services;

import com.dbd23.demo1.library.LibraryException;
import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.repositories.AuthorRepository;
import org.bson.types.ObjectId;
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
    public Author updateAuthor(ObjectId id, String fullname, LocalDate dateOfBirth) throws LibraryException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setFullname(fullname);
            author.setDateOfBirth(dateOfBirth);
            this.authorRepository.save(author);
            return author;
        } else return null;
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) throws LibraryException {
        return this.authorRepository.save(author);
    }

    @Override
    @Transactional
    public boolean deleteAuthor(ObjectId id) throws LibraryException {
        Optional<Author> authorOptional = this.authorRepository.findById(id);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            this.authorRepository.delete(author);
            return true;
        } else return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getById(ObjectId id) {
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
        LocalDate date = LocalDate.now().minusYears(60);
        return this.authorRepository.findByDateOfBirthGreaterThan(date);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorWithMoreBooks() {
        List<Author> authors =  this.authorRepository.findByMoreBooks();
        if(!authors.isEmpty()) {
            return authors.get(0);
        }
        return null;
    }
}
