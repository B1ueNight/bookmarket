package com.csixhsix.bookmarket.api;

import java.util.Map;

import com.csixhsix.bookmarket.data.CompanyVO;
import com.csixhsix.bookmarket.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyAPIController {
    @Autowired CompanyService service;
    @PostMapping("/company/add")
    public Map<String,Object> postCompanyMap(@RequestBody CompanyVO data){
        return service.addCompany(data);
    }
    @DeleteMapping("/company/delete")
    public Map<String, Object> deleteComapny(@RequestParam Integer seq){
        return service.deleteCompany(seq);
    }
    @GetMapping("/company/get")
    public Map<String,Object> getCompanyInfoBySeq(@RequestParam Integer seq) {
        return service.getCompanyInfoBySeq(seq);
    }
    @PatchMapping("/company/update")
    public Map<String, Object> patchCompany(@RequestBody CompanyVO data){
        return service.updateCompany(data);
    }
    @GetMapping("/company/keyword")
    public Map<String, Object> getCompanyByKeyword(@RequestParam @Nullable String keyword) {
        return service.getCompanyByKeyword(keyword);
    }
}
