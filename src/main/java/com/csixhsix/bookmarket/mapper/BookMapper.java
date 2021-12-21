package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.BookVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    public List<BookVO> getBookInfo(Integer offset, String keyword);
    public Integer getBookCount(String keyword);
    public void addBook(BookVO data);
    public void deleteBook(Integer seq);
    public BookVO getBookInfoBySeq(Integer seq);
    public void modifyBook(BookVO data);

    // public Integer selectLatestDataSeq();
    // public void insertCategoryHistory(CategoryHistoryVO data);

    public List<BookVO> getBookByKeyword(String keyword);
}
