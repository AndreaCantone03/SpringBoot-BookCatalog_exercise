package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.Models.User;
import com.example.Lez08Exercise.Models.User_Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<User_Book, Integer> {
    List<User_Book> findByUser(User user);
}
