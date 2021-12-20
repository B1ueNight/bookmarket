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
    public Map<String, Object> getBookList(Integer offset, String keyword, String type) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        System.out.println("####################### getBookList");
        if(keyword == null) {
            resultMap.put("keyword", keyword);
            keyword = "%%";
        }
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        resultMap.put("type", type);

        if(offset == null) offset = 0;
        List<BookVO> list = mapper. getBookList(offset, keyword, type);
        Integer cnt = mapper.getBookCount(type, keyword);

        Integer page = cnt / 10;
        if(cnt % 10 > 0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }

    public Map<String, Object> addBook(BookVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getBi_cop() == null || data.getBi_cop().equals("")) {
            resultMap.put("status", false);
            resultMap.put("reason", "company");
            resultMap.put("message", "출판사를 입력하세요");
            return resultMap;
        }
        if(data.getBi_writer() == null || data.getBi_writer().equals("")) {
            resultMap.put("status", false);
            resultMap.put("reason", "writer");
            resultMap.put("message", "저자를 입력하세요(외의 경우 미상으로 등록)");
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
    public Map<String, Object> getBookInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getBookInfoBySeq(seq));
        return resultMap;
    }
    public Map<String, Object> updateBook(BookVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.updateBook(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");
        return resultMap;
    }
}
