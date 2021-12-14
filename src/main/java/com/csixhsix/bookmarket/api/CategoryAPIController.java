package com.csixhsix.bookmarket.api;

import java.util.Map;

import com.csixhsix.bookmarket.data.CategoryVO;
import com.csixhsix.bookmarket.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryAPIController {
    @Autowired CategoryService service;
    @PostMapping("/category/add")
    public Map<String,Object> postCategoryMap(@RequestBody CategoryVO data){
        return service.addCategory(data);
    }
    @DeleteMapping("/category/delete")
    public Map<String, Object> deleteCategory(@RequestParam Integer seq){
        return service.deleteCategory(seq);
    }
    @GetMapping("/category/get")
    public Map<String,Object> getCategoryInfoBySeq(@RequestParam Integer seq) {
        return service.getCategoryInfoBySeq(seq);
    }
    @PatchMapping("/category/update")
    public Map<String,Object> patchCategoryInfo(@RequestBody CategoryVO data) {
        return service.updateCategory(data);
    }

    
}
