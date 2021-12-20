package com.csixhsix.bookmarket.api;

import java.util.Map;

import com.csixhsix.bookmarket.data.BookVO;
import com.csixhsix.bookmarket.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookAPIController {
    @Autowired BookService service;
    @PostMapping("/book/add")
    public Map<String,Object> postBookMap(@RequestBody BookVO data) throws Exception{
        return service.addBook(data);
    }
}
