package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class CompanyVO {
    private Integer ci_seq;
    private Integer ci_wi_seq;
    private String ci_name;
    private String ci_address;
    private String ci_phone;
    private String ci_email;
    private Date ci_reg_dt;
    
}
