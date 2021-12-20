package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.BookVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    public List<BookVO> getBookList(Integer offset, String type, String keyword);
    public Integer getBookCount(String type, String keyword);
    public void addBook(BookVO data);
    public void deleteBook(Integer seq);
    public BookVO getBookInfoBySeq(Integer seq);
    public void updateBook(BookVO data);
}
