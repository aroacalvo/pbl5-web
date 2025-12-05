package edu.mondragon.we2.pinkAlert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Show login page for "/" and "/login"
    @GetMapping({ "/", "/login" })
    public String showLoginPage() {
        return "login"; // -> /WEB-INF/jsp/login.jsp (see step 3)
    }

    // Handle login POST
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // Dummy login logic – replace with real auth later
        if (username.equals("doctor") && password.equals("123")) {
            return "redirect:/doctor/dashboard";
        }
        if (username.equals("patient") && password.equals("123")) {
            return "redirect:/patient/portal";
        }
        if (username.equals("admin") && password.equals("123")) {
            return "redirect:/admin/dashboard";
        }

        // If invalid → show login page again with error
        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }
}
