package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookHistoryVO {
    private Integer bkh_seq;
    private String bkh_type;
    private String bkh_content;
    private Date bkh_reg_dt;
    private Integer bkh_book_seq;
    
}
