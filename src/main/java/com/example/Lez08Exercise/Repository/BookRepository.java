package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.Models.Book;
import com.example.Lez08Exercise.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
