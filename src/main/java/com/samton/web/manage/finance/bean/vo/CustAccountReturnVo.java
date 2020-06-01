package com.samton.web.manage.finance.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samton.web.manage.finance.bean.entity.TCustAccountReturn;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 还款记录查询
 * @Author: ZhongShengbin
 * @Date: 2020/04/14 14:23
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustAccountReturnVo extends TCustAccountReturn {

    //客户名称
    private String custName;

    //公司名字
    private String companyName;

    //还款时间before
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTimeBegin;

    //还款时间after
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnTimeEnd;

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

    public Date getReturnTimeBegin() {
        return returnTimeBegin;
    }

    public void setReturnTimeBegin(Date returnTimeBegin) {
        this.returnTimeBegin = returnTimeBegin;
    }

    public Date getReturnTimeEnd() {
        return returnTimeEnd;
    }

    public void setReturnTimeEnd(Date returnTimeEnd) {
        this.returnTimeEnd = returnTimeEnd;
    }
}
