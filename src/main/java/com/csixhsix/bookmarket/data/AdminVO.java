package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class AdminVO {
    private Integer ai_seq;
    private String ai_id;
    private String ai_pwd;
    private String ai_name;
    private String ai_email;
    private Date ai_reg_dt;
    private Date ai_login_dt;
    private Integer ai_role;
    private Integer ai_status;
}
