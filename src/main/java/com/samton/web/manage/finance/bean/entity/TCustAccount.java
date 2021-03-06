package com.samton.web.manage.finance.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Description: 客户账户实体
 * @Author: YangYangen
 * @Date: 2020/4/15 14:42
 * Copyright (c) 2020, Samton. All rights reserved
*/
public class TCustAccount extends BaseBean implements Serializable {

    private Integer accountId;

    private Integer custId;

    private BigDecimal accountMoney;

    private BigDecimal creditMoney;

    private BigDecimal dollarRate;

    private Integer isForbid;

    private Long createUserId;

    private Date createDate;

    private Long modifyUserId;

    private Date modifyDate;

    private BigDecimal freightMoney;

    private static final long serialVersionUID = 1L;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
    }

    public Integer getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(Integer isForbid) {
        this.isForbid = isForbid;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BigDecimal getFreightMoney() {
        return freightMoney;
    }

    public void setFreightMoney(BigDecimal freightMoney) {
        this.freightMoney = freightMoney;
    }
}