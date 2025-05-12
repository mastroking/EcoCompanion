package com.example.EcoCompanion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";  // Si riferisce al file 'src/main/resources/static/index.html'
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Se hai un file 'login.html' nella cartella templates
    }

    @GetMapping("/register")
    public String register() {
        return "register";  // Se hai un file 'register.html'
    }
}