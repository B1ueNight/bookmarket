package com.csixhsix.bookmarket.controller;

import com.csixhsix.bookmarket.service.WriterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WriterController {
    @Autowired WriterService service;
    @GetMapping("/writer")
    public String getBook(Model model, @RequestParam @Nullable Integer offset, @RequestParam @Nullable String type, @RequestParam @Nullable String keyword) {
        model.addAttribute("data", service.getWriterList(type, keyword, offset));
        return "/writer/list";
    }
}
