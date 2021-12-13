package com.csixhsix.bookmarket.mapper;

import java.util.List;

import com.csixhsix.bookmarket.data.AdminVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public List<AdminVO> getAdminInfo(Integer offset);
    public Integer getAdminCount();
    public void addAdmin(AdminVO data);
    public void deleteAdmin(Integer seq);
    public void modifyAdmin(AdminVO data);
}
