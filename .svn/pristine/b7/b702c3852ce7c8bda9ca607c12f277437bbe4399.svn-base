package com.samton.web.manage.finance.bean.vo;

import com.samton.web.manage.finance.bean.entity.TCustAccountCredit;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户授信记录
 * @Author: ZhongShengbin
 * @Date: 2020/04/13 16:22
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustAccountCreditVo extends TCustAccountCredit {
    private String custName;

    private String companyName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creditTimeBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creditTimeEnd;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreditTimeEnd() {
        return creditTimeEnd;
    }

    public void setCreditTimeEnd(Date creditTimeEnd) {
        this.creditTimeEnd = creditTimeEnd;
    }

    public Date getCreditTimeBegin() {
        return creditTimeBegin;
    }

    public void setCreditTimeBegin(Date creditTimeBegin) {
        this.creditTimeBegin = creditTimeBegin;
    }
}
