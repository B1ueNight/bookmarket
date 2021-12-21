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
        if(offset == null) {
            offset=0;
            resultMap.put("offset", offset);}
        if(keyword == null) {
            keyword="%%";
            resultMap.put("offset", offset);}
        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";}
            
        List<BookVO> list = mapper.getBookInfo(offset, keyword);

        Integer cnt = mapper.getBookCount(keyword);
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
        
        mapper.addBook(data);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 추가되었습니다.");

        // Integer seq = mapper.selectLatestDataSeq();
        // CategoryHistoryVO history = new CategoryHistoryVO();
        // history.setCath_cate_seq(seq);
        // history.setCath_type("new");
        // String content = data.getCate_name()+"|"+data.getCate_code();
        // history.setCath_content(content);

        // mapper.insertCategoryHistory(history);
        
        return resultMap;
    }

    public Map<String, Object> deleteBook(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 삭제되었습니다.");
        
        // CategoryHistoryVO history = new CategoryHistoryVO();
        // history.setCath_cate_seq(seq);
        // history.setCath_type("delete");

        // mapper.insertCategoryHistory(history);

        return resultMap;
    
    }
    public Map<String, Object> getBookInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getBookInfoBySeq(seq));
        return resultMap;
    }
    public Map<String, Object> modifyBook(BookVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.modifyBook(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        // CategoryHistoryVO history = new CategoryHistoryVO();
        // history.setCath_cate_seq(data.getCate_seq());
        // history.setCath_type("update");
        // String content = data.getCate_name()+"|"+data.getCate_code();
        // history.setCath_content(content);

        // mapper.insertCategoryHistory(history);

        return resultMap;
    }

    public Map<String, Object> getBookByKeyword(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(keyword == null) keyword = "%%";
        keyword = "%"+keyword+"%";
        List<BookVO> list = mapper. getBookByKeyword(keyword);
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }
}