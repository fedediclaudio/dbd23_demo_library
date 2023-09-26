package com.dbd23.demo1.library.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Physical")
public class PhysicalBook extends Book{

    @Column
    float weight;

    @Column
    String location;

    public PhysicalBook() {
    }

    public PhysicalBook(String isbn, String title, int year, String comments, float weight, String location, Author author) {
        super(isbn, title, year, comments, author);
        this.weight = weight;
        this.location = location;
    }

    public PhysicalBook(Long id, String isbn, String title, int year, String comments, float weight, String location, Author author) {
        super(id, isbn, title, year, comments, author);
        this.weight = weight;
        this.location = location;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
