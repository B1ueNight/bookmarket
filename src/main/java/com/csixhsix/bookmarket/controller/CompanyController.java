package com.csixhsix.bookmarket.controller;

import java.util.Map;

import com.csixhsix.bookmarket.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompanyController {
    @Autowired CompanyService service;
    @GetMapping("/company")
    public String getBook(Model model, @RequestParam @Nullable Integer offset, @RequestParam @Nullable String keyword,
    @RequestParam @Nullable String type) {
        Map<String, Object> resultMap = service.getCompanyList(offset, keyword, type);
        model.addAttribute("data", resultMap);
        return "/company/list";
    }
}
