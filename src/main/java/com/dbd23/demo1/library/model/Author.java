package com.dbd23.demo1.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    Long id;

    @Column(length = 100)
    String fullname;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String fullname, LocalDate dateOfBirth) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
    }

    public Author(Long id, String fullname, LocalDate dateOfBirth) {
        this.id = id;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
