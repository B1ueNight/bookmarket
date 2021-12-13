package com.csixhsix.bookmarket.api;

import java.util.Map;

import com.csixhsix.bookmarket.data.BookVO;
import com.csixhsix.bookmarket.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookAPIController {
    @Autowired BookService service;
    @PostMapping("/book/add")
    public Map<String,Object> postBookMap(@RequestBody BookVO data){
        return service.addBook(data);
    }
    @DeleteMapping("/book/delete")
    public Map<String, Object> deleteBook(@RequestParam Integer seq){
        return service.deleteBook(seq);
    }
    @PatchMapping("/book/modify")
    public Map<String, Object> patchBook(@RequestBody BookVO data){
        return service.modifyBook(data);
    }
}
