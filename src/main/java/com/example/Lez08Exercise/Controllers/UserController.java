package com.example.Lez08Exercise.Controllers;

import com.example.Lez08Exercise.Repository.UserRepository;
import com.example.Lez08Exercise.Models.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    private String templatePath = "UsersControllerTemplates";
    String userControllerPath = "users";

    boolean checkValidSession(HttpSession httpSession) {
        return httpSession.getAttribute("id") == null || userRepository.findById((Integer) httpSession.getAttribute("id")).isEmpty();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(HttpSession httpSession) {
        return userRepository.findAll(); //select * from User
    }

    @GetMapping(value = "/registration")
    public String registration(User user, Model model, HttpSession httpSession) {
        System.out.println("Session: "+httpSession.getAttribute("id"));
        if (!checkValidSession(httpSession)) return "redirect:/homepage";
        model.addAttribute("nextPage", "registration");
        return templatePath+"/registrationPage";
    }

    @PostMapping(value = "/registration")
    public String addNewPerson(@Valid User user, BindingResult bindingResult, HttpSession httpSession) {
        if (!checkValidSession(httpSession)) return "redirect:/homepage";
        System.out.println(user.toString());
        if (bindingResult.hasErrors())
            return "registrationPage";
        userRepository.save(user);
        httpSession.setAttribute("id", user.getId());
        return "redirect:/homepage";
    }

    @GetMapping(value = "/login")
    public String login(Model model, HttpSession httpSession) {
        if (!checkValidSession(httpSession)) return "redirect:/homepage";
        model.addAttribute("errText", "");
        return templatePath+"/loginPage";
    }

    @PostMapping(value = "/login")
    public String logUser(@RequestParam("username") String username, @RequestParam("password") String pw, Model model, HttpSession httpSession) {
        if (!checkValidSession(httpSession)) return "redirect:/homepage";
        User user = userRepository.login(username, pw);
        if (user == null) {
            model.addAttribute("errText", "Something went wrong during the authentication");
            return templatePath+"/loginPage";
        }
        httpSession.setAttribute("id", user.getId());
        return "redirect:/homepage";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession httpSession) {
        if (checkValidSession(httpSession)) return "redirect:/";
        System.out.println("Session: "+httpSession.isNew()+" "+httpSession.getId());
        httpSession.setAttribute("id", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/details")
    private String userDetails(Model model, HttpSession httpSession) {
        if (checkValidSession(httpSession)) return "redirect:/";
        System.out.println("User: "+userRepository.findById((Integer) httpSession.getAttribute("id")).get().toString());
        User user = userRepository.findById((Integer) httpSession.getAttribute("id")).get();
        model.addAttribute("user", user);
        return templatePath+"/details";
    }

    @GetMapping(value = "/changedetails")
    private String changeDetailsGet(Model model, HttpSession httpSession) {
        if (checkValidSession(httpSession)) {
            return "redirect:/";
        }
        model.addAttribute("nextPage", "changedetails");
        model.addAttribute("user", userRepository.findById((Integer) httpSession.getAttribute("id")).get());
        return templatePath+"/registrationPage";
    }

    @PostMapping(value = "/changedetails")
    private String changeDetailsPost(@Valid User user, BindingResult bindingResult, HttpSession httpSession) {
        if (checkValidSession(httpSession)) return "redirect:/";
        System.out.println(user.toString());
        if(bindingResult.hasErrors()) return templatePath+"/registrationPage";
        userRepository.save(user);
        return "redirect:/"+userControllerPath+"/details";
    }
}
