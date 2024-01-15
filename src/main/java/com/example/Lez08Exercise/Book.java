package com.example.Lez08Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @NotNull
    @Size(min=1)
    private String title;
    @NotNull
    @Size(min=2)
    private String author;
    private Date isbn;
    @NotNull(message = "You must insert a value")
    private float price;

    public Book(String title, String author, Date isbn, float price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getIsbn() {
        return isbn;
    }

    public String getDateString() {
        return isbn.getYear()+"/"+(isbn.getMonth()+1)+"/"+(isbn.getDate());
    }

    public void setIsbn(Date isbn) {
        this.isbn = isbn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
