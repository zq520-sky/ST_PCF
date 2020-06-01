package com.samton.web.manage.finance.bean.vo;

import com.samton.web.manage.finance.bean.entity.TCustDepositOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户充值记录查询
 * @Author: ZhongShengbin
 * @Date: 2020/04/10 1605
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustDepositOrderVo extends TCustDepositOrder {

    private String file;

    private String custName;

    private String companyName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTimeBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTimeEnd;

    private String repayType;

    private String payTypeCN;

    private String approveStateCN;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

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

    public Date getPayTimeBegin() {
        return payTimeBegin;
    }

    public void setPayTimeBegin(Date payTimeBegin) {
        this.payTimeBegin = payTimeBegin;
    }

    public Date getPayTimeEnd() {
        return payTimeEnd;
    }

    public void setPayTimeEnd(Date payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getPayTypeCN() {
        return payTypeCN;
    }

    public void setPayTypeCN(String payTypeCN) {
        this.payTypeCN = payTypeCN;
    }

    public String getApproveStateCN() {
        return approveStateCN;
    }

    public void setApproveStateCN(String approveStateCN) {
        this.approveStateCN = approveStateCN;
    }
}
