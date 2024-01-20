package com.example.Lez08Exercise;

import com.example.Lez08Exercise.Models.Book;
import com.example.Lez08Exercise.Models.User;
import com.example.Lez08Exercise.Models.User_Book;
import com.example.Lez08Exercise.Repository.BookRepository;
import com.example.Lez08Exercise.Repository.UserBookRepository;
import com.example.Lez08Exercise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Book book = new Book("title", "author", new Date(2024, Calendar.JANUARY, 18), 10);
		bookRepository.save(book);
		User user = new User("name", "surname", "username", "pw");
		userRepository.save(user);
		userBookRepository.save(new User_Book(user, book));
		Book book2 = new Book("title2", "author2", new Date(2024, Calendar.JANUARY, 19), 20);
		bookRepository.save(book2);
		User user2 = new User("name2", "surname2", "username2", "pw2");
		userRepository.save(user2);
		userBookRepository.save(new User_Book(user, book2));
		Book book3 = new Book("title3", "author3", new Date(2024, Calendar.JANUARY, 19), 20);
		bookRepository.save(book3);
		User user3 = new User("name3", "surname3", "username3", "pw3");
		userRepository.save(user3);
		userBookRepository.save(new User_Book(user3, book3));
		Book book4 = new Book("title4", "author4", new Date(2024, Calendar.JANUARY, 19), 20);
		bookRepository.save(book4);
		User user4 = new User("name4", "surname4", "username4", "pw4");
		userRepository.save(user4);
	}
}
