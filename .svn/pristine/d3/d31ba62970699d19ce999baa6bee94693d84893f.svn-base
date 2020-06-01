package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.finance.bean.entity.TCustAccount;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 客户账号Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 11:19
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustAccountEntity extends TCustAccount implements Serializable {

    @Excel(name = "客户编号",width = 15)
    private String custCode;

    @Excel(name = "客户名称",width = 15)
    private String custName;

    @Excel(name = "所属公司",width = 30)
    private String companyName;

    @Excel(name = "美元汇率",width = 30)
    private BigDecimal dollarRate;

    @Excel(name = "账户余额（元）",width = 30)
    private BigDecimal accountMoney;

    @Excel(name = "授信余额（元）",width = 30)
    private BigDecimal creditMoney;

    public String getCustCode() {
        return custCode;
    }

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
    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    @Override
    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
    }

    @Override
    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    @Override
    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    @Override
    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    @Override
    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }
}
