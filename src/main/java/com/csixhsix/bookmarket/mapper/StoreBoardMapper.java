package com.csixhsix.bookmarket.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreBoardMapper {
    public Integer TotalBookCnt();
    public Integer sellOffBookCnt();
    public Integer NewBookCnt();
    public Integer CategoryCnt();

    public Integer TotalUserCnt();
    public Integer NewUserCnt();
    public Integer LeaveUserCnt();

    public Integer TotalCompanyCnt();

    public Integer TotalWriterCnt();

    public Date getBookUpdateDate();
    public Date getCompanyUpdateDate();
    public Date getWriterUpdateDate();

}
