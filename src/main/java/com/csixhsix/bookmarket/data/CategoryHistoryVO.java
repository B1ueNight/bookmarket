package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryHistoryVO {
    private Integer cath_seq;
    private String cath_type;
    private String cath_content;
    private Date cath_reg_dt;
    private Integer cath_cate_seq;
}
