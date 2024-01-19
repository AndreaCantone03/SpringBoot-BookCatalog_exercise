package com.example.Lez08Exercise;

import com.example.Lez08Exercise.Models.Book;
import com.example.Lez08Exercise.Models.User;
import com.example.Lez08Exercise.Models.User_Book;
import com.example.Lez08Exercise.Repository.BookRepository;
import com.example.Lez08Exercise.Repository.UserBookRepository;
import com.example.Lez08Exercise.Repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class Lez08ExerciseApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserBookRepository userBookRepository;
	public static void main(String[] args) {
		SpringApplication.run(Lez08ExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book("nome", "autore", new Date(2024, Calendar.JANUARY, 18), 10);
		bookRepository.save(book);
		User user = new User("ab", "cd", "abcd", "pw");
		userRepository.save(user);
		userBookRepository.save(new User_Book(user, book));
		Book book2 = new Book("bookName", "author", new Date(2024, Calendar.JANUARY, 19), 20);
		bookRepository.save(book2);
		User user2 = new User("John", "Doe", "JD", "Pw");
		userRepository.save(user2);
	}
}
