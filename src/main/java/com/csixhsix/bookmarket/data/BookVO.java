package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookVO {
    private Integer bi_seq;
    private Integer bi_ci_seq;
    private Integer bi_ui_seq;
    private String bi_si_seq;
    private Integer bi_wi_seq;
    private String bi_name;
    private String bi_sub;
    private String bi_cop;
    private String bi_cate;
    private Integer bi_status;
    private Integer bi_stock;
    private Date bi_pub_dt;
    private String bi_writer;
    private Integer bi_point;
    private Date bi_reg_dt;
    private Date bi_mod_dt;
}
