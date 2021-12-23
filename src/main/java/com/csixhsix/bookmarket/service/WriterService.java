package com.csixhsix.bookmarket.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.data.WriterHistoryVO;
import com.csixhsix.bookmarket.data.WriterVO;
import com.csixhsix.bookmarket.mapper.WriterMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriterService {
    @Autowired WriterMapper mapper;
    public Map<String, Object> getWriterList(String type, String keyword, Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
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
        List<WriterVO> list = mapper. getWriterList(type, keyword, offset);
        Integer cnt = mapper.getWriterCnt(type, keyword);

        Integer page = cnt / 10;
        if(cnt % 10 > 0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }

    public Map<String, Object> addWriter(WriterVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getWi_name() == null || data.getWi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "작가명을 입력하세요");
            return resultMap;
        }
        if(data.getWi_email()== null || data.getWi_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일을 입력하세요");
            return resultMap;
        }
        if(data.getWi_birth()== null || data.getWi_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "생년월일(8자리)을 입력하세요");
            return resultMap;
        }
        if(data.getWi_ci_seq()== null || data.getWi_ci_seq().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "소속 출판사를 입력하세요(없으면 '없음' 선택");
            return resultMap;
        }
        
        mapper.addWriter(data);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 추가되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        WriterHistoryVO history = new WriterHistoryVO();
        history.setWrih_writer_seq(seq);
        history.setWrih_type("new");
        String content = data.getWi_name()+"|"+data.getWi_birth()+"|"+data.getWi_ci_seq()+"|"+data.getWi_email();
        history.setWrih_content(content);

        mapper.insertWriterHistory(history);
        
        return resultMap;
    }

    public Map<String, Object> deleteWriter(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteWriter(seq);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 삭제되었습니다.");

        WriterHistoryVO history = new WriterHistoryVO();
        history.setWrih_writer_seq(seq);
        history.setWrih_type("delete");

        mapper.insertWriterHistory(history);

        return resultMap;
    
    }

    public Map<String, Object> updateWriter(WriterVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateWriter(data);
        
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보가 수정되었습니다.");

        Integer seq = mapper.selectLatestDataSeq();
        WriterHistoryVO history = new WriterHistoryVO();
        history.setWrih_writer_seq(seq);
        history.setWrih_type("update");
        String content = data.getWi_name()+"|"+data.getWi_birth()+"|"+data.getWi_ci_seq()+"|"+data.getWi_email();
        history.setWrih_content(content);

        mapper.insertWriterHistory(history);

        return resultMap;
    }

    public Map<String, Object> getWriterInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("data", mapper.getWriterInfoBySeq(seq));
        return resultMap;
    }
}
