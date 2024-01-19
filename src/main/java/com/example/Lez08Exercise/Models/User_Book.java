package com.example.Lez08Exercise.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "users_books")
public class User_Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Book book;

    public User_Book() {
    }

    public User_Book(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
