package com.csixhsix.bookmarket.api;

import java.util.Map;

import com.csixhsix.bookmarket.data.WriterVO;
import com.csixhsix.bookmarket.service.WriterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WriterAPIController {
    @Autowired WriterService service;
    @PostMapping("/writer/add")
    public Map<String,Object> postWriterMap(@RequestBody WriterVO data) throws Exception{
        return service.addWriter(data);
    }
    @DeleteMapping("/writer/delete")
    public Map<String, Object> deleteWriter(@RequestParam Integer seq){
        return service.deleteWriter(seq);
    }
    @PatchMapping("/writer/update")
    public Map<String, Object> patchWriter(@RequestBody WriterVO data){
        return service.updateWriter(data);
    }
    @GetMapping("/writer/get")
    public Map<String,Object> getWriterInfoBySeq(@RequestParam Integer seq) {
        return service.getWriterInfoBySeq(seq);
    }
}
