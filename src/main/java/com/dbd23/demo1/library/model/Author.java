package com.dbd23.demo1.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
public class Author {

    @MongoId
    ObjectId id;

    @Field(name = "fullname")
    String fullname;

    @Field
    LocalDate dateOfBirth;

    @DBRef
    @JsonBackReference
    private List<Book> books = new ArrayList<>();

    @Version
    @Field
    private int version;

    public Author() {
    }

    public Author(String fullname, LocalDate dateOfBirth) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
    }

    public Author(ObjectId id, String fullname, LocalDate dateOfBirth) {
        this.id = id;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
