package com.dbd23.demo1.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int year;

    @Column(length = 255)
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;

    @Transient // Indica que dicho atributo no se mapea a la base de datos.
    private Editorial editorial;

    @Transient
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

    public Book(Long id, String isbn, String title, int year, String comments, Author author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.comments = comments;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
