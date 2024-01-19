package com.example.Lez08Exercise.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    @NotNull
    @Size(min=1)
    private String title;
    @NotBlank
    @NotNull
    @Size(min=2)
    private String author;
    @NotNull
    private Date year;
    @NotNull(message = "You must insert a value")
    private float price;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<User_Book> users_books;

    public Book() {}

    public Book(String title, String author, Date year, float price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getYear() {
        return year;
    }

    public String getDateString() {
        return year.getYear()+"/"+(year.getMonth()+1)+"/"+(year.getDate());
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
