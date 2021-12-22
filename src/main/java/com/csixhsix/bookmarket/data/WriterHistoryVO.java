package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class WriterHistoryVO {
    private Integer wrih_seq;
    private String wrih_type;
    private String wrih_content;
    private Integer wrih_writer_seq;
    private Date wrih_reg_dt;
}
