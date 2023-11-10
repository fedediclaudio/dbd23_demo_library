package com.dbd23.demo1.library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

public abstract class Book {

    @MongoId
    private ObjectId id;

    @Field
    private String isbn;

    @Field
    private String title;

    @Field
    private int year;

    @Field
    private String comments;

    @Field
    @JsonManagedReference
    private Author author;

    @BsonIgnore // Ignora esta propiedad, ya que no estan mapeadas las Propiedades en esta muesta
    private Editorial editorial;

    @BsonIgnore
    private List<Genre> genre;

    public Book() {
    }

    public Book(String isbn, String title, int year, String comments, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.comments = comments;
        this.author = author;
    }

    public Book(ObjectId id, String isbn, String title, int year, String comments, Author author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.comments = comments;
        this.author = author;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
