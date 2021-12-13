package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.CategoryVO;
import com.csixhsix.bookmarket.mapper.CategoryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired CategoryMapper mapper;
    public Map<String, Object> getCategoryList(Integer offset) {
        if(offset == null) offset=0;

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<CategoryVO> list = mapper.getCategoryInfo(offset);

        Integer cnt = mapper.getCategoryCount();
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
        
        return resultMap;
    }

    public Map<String, Object> deleteCategory(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteCategory(seq);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 삭제되었습니다.");
        return resultMap;
    
    }

    public Map<String, Object> modifyCategory(CategoryVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.modifyCategory(data);
        resultMap.put("status", true);
        resultMap.put("message", "카테고리가 수정되었습니다.");
        return resultMap;
    }


}
