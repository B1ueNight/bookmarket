package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVO {
    private Integer cate_seq;
    private Integer cate_bi_seq;
    private String cate_name;
    private String cate_code;
    private Date cate_reg_dt;
    private Integer cate_stock;
}
