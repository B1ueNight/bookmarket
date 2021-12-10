package com.csixhsix.bookmarket.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csixhsix.bookmarket.mapper.StoreBoardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {
    @Autowired StoreBoardMapper mapper;

    public Map<String, Object> getCount() {
        List<Integer> bookCntList = new ArrayList<Integer>();
        bookCntList.add(mapper.TotalBookCnt());
        bookCntList.add(mapper.sellOffBookCnt());
        bookCntList.add(mapper.NewBookcnt());
        bookCntList.add(mapper.SoldoutBookcnt());

        List<Integer> userCntList = new ArrayList<Integer>();
        userCntList.add(mapper.TotalUserCnt());
        userCntList.add(mapper.NewUserCnt());
        userCntList.add(mapper.LeaveUserCnt());

        List<Integer> companyCntList = new ArrayList<Integer>();
        companyCntList.add(mapper.TotalCompanyCnt());

        List<Integer> writerCntList = new ArrayList<Integer>();
        writerCntList.add(mapper.TotalWriterCnt());

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("book", bookCntList);
        map.put("user", userCntList);
        map.put("comapny", companyCntList);
        map.put("writer", writerCntList);
        return map;
    
    }
}
