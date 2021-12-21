package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.CompanyHistoryVO;
import com.csixhsix.bookmarket.data.CompanyVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    public List<CompanyVO> getCompanyInfo(Integer offset, String keyword);
    public Integer getCompanyCount(String keyword);
    public void addCompany(CompanyVO data);
    public void deleteCompany(Integer seq);
    public CompanyVO getCompanyInfoBySeq(Integer seq);
    public void updateCompany(CompanyVO data);

    public Integer selectLatestDataSeq();
    public void insertCompanyHistory(CompanyHistoryVO data);

    public List<CompanyVO> getCompanyByKeyword(String keyword);
}
