package com.example.Lez08Exercise.Controllers;

import com.example.Lez08Exercise.Repository.UserRepository;
import com.example.Lez08Exercise.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll(); //select * from User
    }
}
