package com.csixhsix.bookmarket.controller;

import com.csixhsix.bookmarket.service.BookstoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    BookstoreService service;
    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("cnt", service.getCount());
        return "/index";
    }
    
}
