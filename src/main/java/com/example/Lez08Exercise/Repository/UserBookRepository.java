package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.Models.Book;
import com.example.Lez08Exercise.Models.User_Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<User_Book, Integer> {
    User_Book findByBook(Book book);
    //@Query("SELECT u_b User_Book u_b where user_id= :id")
    //List<User_Book> findByUserId(Integer id);
}
