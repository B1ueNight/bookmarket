package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.BookVO;
import com.csixhsix.bookmarket.mapper.BookMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired BookMapper mapper;
    public Map<String, Object> getBookList(Integer offset) {
        if(offset == null) offset=0;

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<BookVO> list = mapper.getBookInfo(offset);

        Integer cnt = mapper.getBookCount();
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addBook(BookVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getBi_name() == null || data.getBi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "도서명을 입력하세요");
            return resultMap;
        }
        if(data.getBi_cate()== null || data.getBi_cate().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "카테고리를 입력하세요");
            return resultMap;
        }
        if(data.getBi_cop()== null || data.getBi_cop().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "출판사를 입력하세요");
            return resultMap;
        }
        if(data.getBi_writer()== null || data.getBi_writer().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "저자를 입력하세요(외의 경우 미상으로 등록)");
            return resultMap;
        }
        if(data.getBi_status() == null || data.getBi_status() == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "도서 상태를 입력하세요");
            return resultMap;
        }
        if(data.getBi_pub_dt() == null) {
            resultMap.put("status", false);
            resultMap.put("message", "도서 출판일을 입력하세요");
            return resultMap;
        }
        if(data.getBi_stock() == null) {
            resultMap.put("status", false);
            resultMap.put("message", "도서 재고를 입력하세요");
            return resultMap;
        }
        if(data.getBi_point() == null) {
            resultMap.put("status", false);
            resultMap.put("message", "적립포인트를 입력하세요");
            return resultMap;
        }
        
        mapper.addBook(data);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 추가되었습니다.");
        
        return resultMap;
    }

    public Map<String, Object> deleteBook(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 삭제되었습니다.");
        return resultMap;
    
    }

    public Map<String, Object> modifyBook(BookVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.modifyBook(data);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 수정되었습니다.");
        return resultMap;
    }

    
}
