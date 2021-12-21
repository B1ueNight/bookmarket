package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.WriterVO;
import com.csixhsix.bookmarket.mapper.WriterMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterService {
    @Autowired WriterMapper mapper;
    public Map<String, Object> getWriterList(Integer offset) {
        if(offset == null) offset=0;

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<WriterVO> list = mapper.getWriterInfo(offset);

        Integer cnt = mapper.getWriterCount();
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addWriter(WriterVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getWi_name() == null || data.getWi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "출판사명을 입력하세요");
            return resultMap;
        }
        if(data.getWi_email()== null || data.getWi_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일를 입력하세요");
            return resultMap;
        }
        if(data.getWi_birth()== null || data.getWi_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "생년월일(8자리)을 입력하세요");
            return resultMap;
        }
        if(data.getWi_company()== null || data.getWi_company().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "소속 출판사를 입력하세요(없으면 '없음' 선택");
            return resultMap;
        }
        
        mapper.addWriter(data);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 추가되었습니다.");
        
        return resultMap;
    }

    public Map<String, Object> deleteWriter(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteWriter(seq);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 삭제되었습니다.");
        return resultMap;
    
    }

    public Map<String, Object> updateWriter(WriterVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.updateWriter(data);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 수정되었습니다.");
        return resultMap;
    }
}
