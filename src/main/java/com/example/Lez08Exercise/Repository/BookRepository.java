package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
