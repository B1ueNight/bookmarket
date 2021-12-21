package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class WriterVO {
    private Integer wi_seq;
    private Integer wi_ci_seq;
    private String wi_name;
    private String wi_birth;
    private String wi_email;
    private String wi_company;
    private Date wi_reg_dt;
    private Date wi_mod_dt;
    
}
