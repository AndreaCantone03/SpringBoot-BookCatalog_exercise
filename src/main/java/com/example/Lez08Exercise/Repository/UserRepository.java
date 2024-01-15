package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
