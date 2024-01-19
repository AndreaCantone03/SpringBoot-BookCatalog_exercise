package com.example.Lez08Exercise.Controllers;

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
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/books")
public class BookController {
    private String templatePath = "BookControllerTemplates";
    static String bookControllerPath = "books";
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    UserBookRepository userBookRepository;

    @RequestMapping("/")
    private String mainPage(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("id") != null) return "redirect:/homepage";
        model.addAttribute("allBooks", bookRepository.findAll());
        return templatePath+"/mainPage";
    }

    @RequestMapping("/all")
    private @ResponseBody Iterable<Book> all() {
        return bookRepository.findAll();
    }

    @RequestMapping("/alluserbook")
    private @ResponseBody Iterable<User_Book> allUserBook() {
        return userBookRepository.findAll();
    }

    @RequestMapping("/mybooks")
    private String myBooks(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("id") == null) {
            return "redirect:/";
        }
        model.addAttribute("allBooks", bookRepository);
        return templatePath+"myBooks";
    }

    @GetMapping("/addbook")
    private String sendForm(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("id") != null) return "redirect:/homepage";
        return templatePath+"/addBook";
    }

    @RequestMapping("/availableBooks")
    private String googleApiRequest() {
        Gson g = new Gson();
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=quilting");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader responseStream = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(responseStream);
                String jsonResponse;
                StringBuilder response = new StringBuilder();
                while ((jsonResponse = reader.readLine()) != null) {
                    response.append(jsonResponse);
                }
                reader.close();
                System.out.println(response.toString());
                JsonObject jsonObject = g.fromJson(response.toString(), JsonObject.class);
                JsonArray jsonArray = jsonObject.getAsJsonArray("items");
                for(JsonElement book : jsonArray) {
                    JsonObject item = book.getAsJsonObject();
                    Book newBook = new Book(
                            item.getAsJsonObject("volumeInfo").getAsJsonPrimitive("title").getAsString(),
                            item.getAsJsonObject("volumeInfo").getAsJsonArray("authors").get(0).getAsString(),
                            new Date(2024, Calendar.JANUARY, 19/*item.getAsJsonObject("volumeInfo").getAsJsonPrimitive("publishedDate").getAsString()*/),
                            item.getAsJsonObject("saleInfo").getAsJsonObject("listPrice").getAsJsonPrimitive("amount").getAsFloat()
                    );
                    bookRepository.save(newBook);
                    System.out.println(newBook.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
