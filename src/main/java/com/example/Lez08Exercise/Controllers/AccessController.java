package com.example.Lez08Exercise.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {
    private String mainTemplatePath = "AccessControllerTemplates";

    @RequestMapping("/")
    private String getFirstPage(HttpSession httpSession) {
        if (httpSession.getAttribute("id") != null) return "redirect:/homepage";
        return mainTemplatePath+"/firstPage";
    }

    @RequestMapping("/homepage")
    private String getHomePage(HttpSession httpSession) {
        if (httpSession.getAttribute("id") == null) return "redirect:/";
        return mainTemplatePath+"/homePage";
    }
}
