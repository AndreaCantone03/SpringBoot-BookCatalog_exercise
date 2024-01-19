package com.example.Lez08Exercise.Repository;

import com.example.Lez08Exercise.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsername(String username);
    User findUserById(Integer id);

    @Query("SELECT u from User u where username= :username and password= :password")
    public User login(String username, String password);
}
