package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.WriterVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriterMapper {
    public List<WriterVO> getWriterInfo(Integer offset);
    public Integer getWriterCount();
    public void addWriter(WriterVO data);
    public void deleteWriter(Integer seq);
    public void updateWriter(WriterVO data);
}
