package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.WriterHistoryVO;
import com.csixhsix.bookmarket.data.WriterVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WriterMapper {
    public List<WriterVO> getWriterList(String type, String keyword, Integer offset);
    public Integer getWriterCnt(String type, String keyword);
    public List<WriterVO> getWriterInfo(Integer offset);
    public Integer getWriterCount();
    public WriterVO getWriterInfoBySeq(Integer seq);
    public void addWriter(WriterVO data);
    public void deleteWriter(Integer seq);
    public void updateWriter(WriterVO data);

    public Integer selectLatestDataSeq();
    public void insertWriterHistory(WriterHistoryVO data);
}
