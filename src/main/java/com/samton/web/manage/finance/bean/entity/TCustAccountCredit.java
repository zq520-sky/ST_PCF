package com.samton.web.manage.finance.bean.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TCustAccountCredit implements Serializable {
    private Integer creditId;

    private Integer accountId;

    private Integer custId;

    private BigDecimal creditMoney;

    private BigDecimal beforeCreditMoney;

    private BigDecimal afterCreditMoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creditTime;

    private Integer isClose;

    private String remark;

    private Long createUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Long modifyUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    private String custCode;

    private Double dollarRate;

    private static final long serialVersionUID = 1L;

    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

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

    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    public BigDecimal getBeforeCreditMoney() {
        return beforeCreditMoney;
    }

    public void setBeforeCreditMoney(BigDecimal beforeCreditMoney) {
        this.beforeCreditMoney = beforeCreditMoney;
    }

    public BigDecimal getAfterCreditMoney() {
        return afterCreditMoney;
    }

    public void setAfterCreditMoney(BigDecimal afterCreditMoney) {
        this.afterCreditMoney = afterCreditMoney;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(Date creditTime) {
        this.creditTime = creditTime;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(Double dollarRate) {
        this.dollarRate = dollarRate;
    }
}