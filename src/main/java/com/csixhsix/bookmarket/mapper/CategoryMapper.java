package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.CategoryVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    public List<CategoryVO> getCategoryInfo(Integer offset, String keyword);
    public Integer getCategoryCount(String keyword);
    public void addCategory(CategoryVO data);
    public void deleteCategory(Integer seq);
    public CategoryVO getCategoryInfoBySeq(Integer seq);
    public void updateCategory(CategoryVO data);
}
