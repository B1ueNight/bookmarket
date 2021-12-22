package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.BookHistoryVO;
import com.csixhsix.bookmarket.data.BookVO;
import com.csixhsix.bookmarket.mapper.BookMapper;
import com.csixhsix.bookmarket.utils.AESAlgorithm;

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

    public Map<String, Object> addBook(BookVO data) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getBi_name() == null || data.getBi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "도서명을 입력하세요");
            return resultMap;
        }
        
        mapper.addBook(data);
        resultMap.put("status", true);
        resultMap.put("message", "도서가 추가되었습니다.");
        
        String code = data.getBi_code();
            String encrypted = AESAlgorithm.Encrypt(code);
            data.setBi_code(encrypted);
    
            mapper.addBook(data);
            
        Integer seq = mapper.selectLatestDataSeq();
        BookHistoryVO history = new BookHistoryVO();
        history.setBkh_book_seq(seq);
        history.setBkh_type("new");
        String content = data.getBi_name()+"|"+data.getBi_code()+"|"+data.getBi_company()+"|"+data.getBi_status()+"|"+data.getBi_stock()+"|"+data.getBi_pub_dt()+"|"+data.getBi_point();
        history.setBkh_content(content);

        mapper.insertBookHistory(history);
        

        return resultMap;
    }




    public Map<String, Object> deleteBook(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 삭제되었습니다.");
        
        BookHistoryVO history = new BookHistoryVO();
        history.setBkh_book_seq(seq);
        history.setBkh_type("delete");

        mapper.insertBookHistory(history);

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

        BookHistoryVO history = new BookHistoryVO();
        history.setBkh_book_seq(data.getBi_seq());
        history.setBkh_type("update");
        String content = data.getBi_name()+"|"+data.getBi_code()+"|"+data.getBi_company()+"|"+data.getBi_status()+"|"+data.getBi_stock()+"|"+data.getBi_pub_dt()+"|"+data.getBi_point();
        history.setBkh_content(content);

        mapper.insertBookHistory(history);

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