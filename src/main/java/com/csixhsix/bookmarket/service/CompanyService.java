package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.CompanyVO;
import com.csixhsix.bookmarket.mapper.CompanyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired CompanyMapper mapper;
    public Map<String, Object> getCompanyList(Integer offset) {
        if(offset == null) offset=0;

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<CompanyVO> list = mapper.getCompanyInfo(offset);

        Integer cnt = mapper.getCompanyCount();
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addCompany(CompanyVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getCi_name() == null || data.getCi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "출판사명을 입력하세요");
            return resultMap;
        }
        if(data.getCi_email()== null || data.getCi_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일를 입력하세요");
            return resultMap;
        }
        if(data.getCi_phone()== null || data.getCi_phone().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "연락처를 입력하세요");
            return resultMap;
        }
        if(data.getCi_address()== null || data.getCi_address().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "주소를 입력하세요");
            return resultMap;
        }
        
        mapper.addCompany(data);
        resultMap.put("status", true);
        resultMap.put("message", "출판사 정보가 추가되었습니다.");
        
        return resultMap;
    }

    public Map<String, Object> deleteCompany(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteCompany(seq);
        resultMap.put("status", true);
        resultMap.put("message", "출판사 정보가 삭제되었습니다.");
        return resultMap;
    
    }

    public Map<String, Object> modifyCompany(CompanyVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.modifyCompany(data);
        resultMap.put("status", true);
        resultMap.put("message", "출판사 정보가 수정되었습니다.");
        return resultMap;
    }

    
}