package com.example.Lez08Exercise;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class Book {
    @NotNull
    @Size(min=1)
    private String Title;
    private String author;
    private Date isbn;
    @NotNull
    @Min(0)
    private float price;

    public Book(String title, String author, Date isbn, float price) {
        Title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
        return (isbn.getMonth()+1) + "/" + (isbn.getDate()) + "/" + isbn.getYear();
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
