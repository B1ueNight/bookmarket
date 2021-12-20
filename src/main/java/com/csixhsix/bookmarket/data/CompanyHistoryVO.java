package com.csixhsix.bookmarket.data;

import java.util.Date;

import lombok.Data;

@Data
public class CompanyHistoryVO {
    private Integer coph_seq;
    private String coph_type;
    private String coph_content;
    private Date coph_reg_dt;
    private Integer coph_cop_seq;
}
