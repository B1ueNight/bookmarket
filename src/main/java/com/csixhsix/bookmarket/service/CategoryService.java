package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.CategoryHistoryVO;
import com.csixhsix.bookmarket.data.CategoryVO;
import com.csixhsix.bookmarket.mapper.CategoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired CategoryMapper mapper;
    public Map<String, Object> getCategoryList(Integer offset, String keyword) {
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
            
        List<CategoryVO> list = mapper.getCategoryInfo(offset, keyword);

        Integer cnt = mapper.getCategoryCount(keyword);
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addCategory(CategoryVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getCate_name() == null || data.getCate_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "카테고리명을 입력하세요");
            return resultMap;
        }
        if(data.getCate_code()== null || data.getCate_code().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "카테고리 코드를 입력하세요");
            return resultMap;
        }
        
        mapper.addCategory(data);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 추가되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        CategoryHistoryVO history = new CategoryHistoryVO();
        history.setCath_cate_seq(seq);
        history.setCath_type("new");
        String content = data.getCate_name()+"|"+data.getCate_code();
        history.setCath_content(content);

        mapper.insertCategoryHistory(history);
        
        return resultMap;
    }

    public Map<String, Object> deleteCategory(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteCategory(seq);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 삭제되었습니다.");
        
        CategoryHistoryVO history = new CategoryHistoryVO();
        history.setCath_cate_seq(seq);
        history.setCath_type("delete");

        mapper.insertCategoryHistory(history);

        return resultMap;
    
    }
    public Map<String, Object> getCategoryInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getCategoryInfoBySeq(seq));
        return resultMap;
    }
    public Map<String, Object> updateCategory(CategoryVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.updateCategory(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        CategoryHistoryVO history = new CategoryHistoryVO();
        history.setCath_cate_seq(data.getCate_seq());
        history.setCath_type("update");
        String content = data.getCate_name()+"|"+data.getCate_code();
        history.setCath_content(content);

        mapper.insertCategoryHistory(history);

        return resultMap;
    }
}
