package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.finance.bean.entity.TCustAccountCredit;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户授信记录Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 11:30
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustAccountCreditEntity extends TCustAccountCredit {

    @Excel(name = "客户编号", width = 20)
    private String custCode;

    @Excel(name = "客户名称", width = 20)
    private String custName;

    @Excel(name = "所属公司", width = 40)
    private String companyName;

    @Excel(name = "操作前授信余额(元)", width = 20)
    private BigDecimal beforeCreditMoney;

    @Excel(name = "操作后授信余额(元)", width = 20)
    private BigDecimal afterCreditMoney;

    @Excel(name = "授信金额(元)", width = 20)
    private BigDecimal creditMoney;

    @Excel(name = "授信", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date creditTime;

    @Override
    public String getCustCode() {
        return custCode;
    }

    @Override
    public void setCustCode(String custCode) {
        this.custCode = custCode;
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

    @Override
    public BigDecimal getBeforeCreditMoney() {
        return beforeCreditMoney;
    }

    @Override
    public void setBeforeCreditMoney(BigDecimal beforeCreditMoney) {
        this.beforeCreditMoney = beforeCreditMoney;
    }

    @Override
    public BigDecimal getAfterCreditMoney() {
        return afterCreditMoney;
    }

    @Override
    public void setAfterCreditMoney(BigDecimal afterCreditMoney) {
        this.afterCreditMoney = afterCreditMoney;
    }

    @Override
    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    @Override
    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    @Override
    public Date getCreditTime() {
        return creditTime;
    }

    @Override
    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }
}
