package com.csixhsix.bookmarket.controller;

import java.util.Map;

import com.csixhsix.bookmarket.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired CategoryService service;

    @GetMapping("/category")
    public String getCategory(Model model, @RequestParam @Nullable Integer offset, @RequestParam @Nullable String keyword) {
        Map<String, Object> resultMap = service.getCategoryList(offset, keyword);
        model.addAttribute("data", resultMap);
        return "/category/list";
    }
}
