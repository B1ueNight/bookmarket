package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.CompanyHistoryVO;
import com.csixhsix.bookmarket.data.CompanyVO;
import com.csixhsix.bookmarket.mapper.CompanyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired CompanyMapper mapper;
        public Map<String, Object> getCompanyList(Integer offset, String keyword) {
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
                
            List<CompanyVO> list = mapper.getCompanyInfo(offset, keyword);
    
            Integer cnt = mapper.getCompanyCount(keyword);
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
            resultMap.put("message", "이메일을 입력하세요");
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
        
        Integer seq = mapper.selectLatestDataSeq();
        CompanyHistoryVO history = new CompanyHistoryVO();
        history.setCoph_cop_seq(seq);
        history.setCoph_type("new");
        String content = data.getCi_name()+"|"+data.getCi_phone()+"|"+data.getCi_email()+"|"+data.getCi_address();
        history.setCoph_content(content);

        mapper.insertCompanyHistory(history);
        
        return resultMap;
    }

    public Map<String, Object> deleteCompany(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteCompany(seq);
        resultMap.put("status", true);
        resultMap.put("message", "출판사 정보가 삭제되었습니다.");
        return resultMap;
    
    }

    public Map<String, Object> getCompanyInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getCompanyInfoBySeq(seq));
        return resultMap;
    }

    public Map<String, Object> updateCompany(CompanyVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.updateCompany(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        CompanyHistoryVO history = new CompanyHistoryVO();
        history.setCoph_cop_seq(data.getCi_seq());
        history.setCoph_type("update");
        String content = data.getCi_name()+"|"+data.getCi_phone()+"|"+data.getCi_email()+"|"+data.getCi_address();
        history.setCoph_content(content);

        mapper.insertCompanyHistory(history);

        return resultMap;
    }

    public Map<String, Object> getCompanyByKeyword(String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(keyword == null) keyword = "%%";
        keyword = "%"+keyword+"%";
        List<CompanyVO> list = mapper. getCompanyByKeyword(keyword);
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }

}