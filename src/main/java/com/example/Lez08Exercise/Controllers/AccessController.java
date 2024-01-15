package com.example.Lez08Exercise.Controllers;

import com.example.Lez08Exercise.Book;
import com.example.Lez08Exercise.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class AccessController {
    private ArrayList<User> userArrayList = new ArrayList<User>(Arrays.asList(new User("Name", "Surname","Username", "Password"), new User("John", "Doe", "JohnDoe", "JD")));
    private ArrayList<Cookie> cookies = new ArrayList<Cookie>();
    private ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(
            new Book("Math Book", "Math professor", new Date(2024, Calendar.JANUARY, 10), 10.0f),
            new Book("Italian Book", "Italian professor", new Date(2024, Calendar.FEBRUARY, 5), 10.0f))
    );

    private boolean checkCookieSession(Cookie[] requestCookies) {
        for (Cookie c: cookies
             ) {
            for (Cookie requestC: requestCookies
                 ) {
                if (c.getName().equals(requestC.getName()) && c.getValue().equals(requestC.getValue())) return true;
            }
        }
        return false;
    }

    private Cookie addEncryptedCookieSession() {
        String sessionName = "";
        StringBuilder sessionValue = new StringBuilder();
        int nMax = 57, nMin = 48, capitalMax = 90, capitalMin = 65, charMax = 122, charMin = 97;
        /*for (int i = 0; i < 10; i++) {
            int d = (int) (Math.random()*100);
            char newCharacter = ' ';
            if (d <= 33) {
                newCharacter = (char) ((Math.random() * (nMax - nMin)) + nMin);
            } else if (d <= 66) {
                newCharacter = (char) ((Math.random() * (capitalMax - capitalMin)) + capitalMin);
            } else {
                newCharacter = (char) ((Math.random() * (charMax - charMin)) + charMin);
            }
            sessionName += newCharacter;
        }*/
        for (int i = 0; i < 20; i++) {
            int d = (int) (Math.random()*100);
            char newCharacter = ' ';
            if (d <= 33) {
                newCharacter = (char) ((Math.random() * (nMax - nMin)) + nMin);
            } else if (d <= 66) {
                newCharacter = (char) ((Math.random() * (capitalMax - capitalMin)) + capitalMin);
            } else {
                newCharacter = (char) ((Math.random() * (charMax - charMin)) + charMin);
            }
            sessionValue.append(newCharacter);
        }
        System.out.println("SessionValue: " + sessionValue.toString());
        Cookie newCookie = new Cookie("session", sessionValue.toString());
        newCookie.setSecure(true);
        newCookie.setHttpOnly(true);
        cookies.add(newCookie);
        return newCookie;
    }

    @GetMapping(value = "/login")
    public String mainPage(User user, HttpServletRequest request) {
        if (checkCookieSession(request.getCookies())) return "redirect:/";
        for (User person : userArrayList) {
            System.out.println(person.toString());
        }
        return "formPage";
    }

    @PostMapping(value = "/login")
    public String checkPersonInfo(@Valid User user, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        if (checkCookieSession(request.getCookies())) return "redirect:/";
        System.out.println(user.getUsername() + " " + user.getPassword());
        boolean validCredentials = false;
        for (User person : userArrayList) {
            //System.out.println("Checking...");
            if (person.getUsername().equals(user.getUsername()) && person.getPassword().equals(user.getPassword())) {
                validCredentials = true;
                break;
            }
        }
        if (!validCredentials) if (bindingResult.hasErrors()) return "formPage";
        response.addCookie(addEncryptedCookieSession());
        return "redirect:/";
    }

    @GetMapping(value = "/registration")
    public String registration(User user, HttpServletRequest request) {
        if (checkCookieSession(request.getCookies())) return "redirect:/";
        return "registrationPage";
    }

    @PostMapping(value = "/registration")
    public String addNewPerson(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (checkCookieSession(request.getCookies())) return "redirect:/";
        if (bindingResult.hasErrors())
            return "registrationPage";
        userArrayList.add(user);
        System.out.println(userArrayList.size());
        response.addCookie(addEncryptedCookieSession());
        return "redirect:/";
    }

    @RequestMapping("/")
    public String mainPage(Model model, HttpServletRequest request) {
        System.out.println(Arrays.stream(request.getCookies()).map(c -> {return c.getName() + " " + c.getValue() + " " + c.getSecure() + " " + c.isHttpOnly();}).collect(Collectors.joining(", ")));
        if (!checkCookieSession(request.getCookies())) return "redirect:/login";
        model.addAttribute("allBooks", books);
        return "mainPage";
    }

    @GetMapping("/addbook")
    public String bookForm(HttpServletRequest request) {
        if (!checkCookieSession(request.getCookies())) return "redirect:/login";
        System.out.println(request);
        return "addBook";
    }

    @PostMapping("/addbook")
    public String addBook(@RequestParam("title") String title,
                          @RequestParam("author") String author,
                          @RequestParam("date") String date,
                          @RequestParam("price") float price,
                          /*BindingResult bindingResult,*/ HttpServletRequest request) {
        if (!checkCookieSession(request.getCookies())) return "redirect:/login";
        System.out.println(title+" "+author+" "+" "+price+" "+date);
        boolean thereIsAlready = false;
        String pageD = date.replace("-", "/");
        Date d = new Date(pageD);
        for (Book b:
             books) {
            if(b.getTitle().equals(title) && b.getAuthor().equals(author) &&
            b.getDateString().equals(date) && b.getPrice() == price) thereIsAlready = true; break;
        }
        if (!thereIsAlready) {
            books.add(new Book(title, author, d, price));
        }
        /*Book book;
        for (Book b: books) {
            System.out.println("Checking...");
            if (b.getTitle().equals(book.getTitle()) && b.getAuthor().equals(book.getAuthor())) {
                thereIsAlready = true;
                break;
            }
        }*/
        //if (thereIsAlready || bindingResult.hasErrors()) return "addBook";
        //books.add(book);
        return "redirect:/";
    }
}
