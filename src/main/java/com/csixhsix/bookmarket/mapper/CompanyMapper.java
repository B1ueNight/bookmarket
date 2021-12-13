package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.CompanyVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    public List<CompanyVO> getCompanyInfo(Integer offset);
    public Integer getCompanyCount();
    public void addCompany(CompanyVO data);
    public void deleteCompany(Integer seq);
    public void modifyCompany(CompanyVO data);
    
}
