package com.samton.web.manage.cust.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.cust.bean.entity.TCustomer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户信息管理Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 10:11
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustomerEntity extends TCustomer implements Serializable {

    @Excel(name = "客户编号",width = 15)
    private String custCode;

    @Excel(name = "客户名称", width = 15)
    private String custName;

    @Excel(name = "登录账号", width = 30)
    private String loginName;

    @Excel(name = "所属公司", width = 40)
    private String companyName;

    @Excel(name = "联系电话", width = 20)
    private String contactTel;

    @Excel(name = "邮箱", width = 20)
    private String email;

    @Excel(name = "美元汇率", width = 20)
    private BigDecimal dollarRate;

    @Excel(name = "禁用状态", width = 20)
    private String isForbidCn;

    @Override
    public String getCustCode() {
        return custCode;
    }

    @Override
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Override
    public String getCustName() {
        return custName;
    }

    @Override
    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getContactTel() {
        return contactTel;
    }

    @Override
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
    }

    public String getIsForbidCn() {
        return isForbidCn;
    }

    public void setIsForbidCn(String isForbidCn) {
        this.isForbidCn = isForbidCn;
    }
}
