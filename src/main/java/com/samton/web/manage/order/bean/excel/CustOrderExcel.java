package com.samton.web.manage.order.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.order.bean.entity.TCustOrder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户需求订单Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 14:17
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustOrderExcel extends TCustOrder {

    @Excel(name = "订单编号", width = 40)
    private String orderCode;

    @Excel(name = "客户名称", width = 20)
    private String custName;

    @Excel(name = "需求类型", width = 20)
    private String redemandType;

    @Excel(name = "电商平台", width = 20)
    private String platformType;

    @Excel(name = "卖家账号（店铺名称）", width = 20)
    private String accountsName;

    @Excel(name = "产品ID", width = 20)
    private String itemId;

    @Excel(name = "标题（关键词）", width = 40)
    private String titleKeyword;

    @Excel(name = "地址", width = 40)
    private String address;

    @Excel(name = "商品单价（$）", width = 20)
    private BigDecimal unitPrice;

    @Excel(name = "订单数量", width = 20)
    private Integer orderNum;

    @Excel(name = "税收（$）", width = 20)
    private BigDecimal tax;

    @Excel(name = "刷单频次", width = 20)
    private Integer frequency;

    @Excel(name = "刷单周期（天）", width = 20)
    private Integer cycle;

    @Excel(name = "美元汇率", width = 20)
    private BigDecimal dollarRate;

    @Excel(name = "订单总额（元）", width = 20)
    private BigDecimal countPrice;

    @Excel(name = "任务开始日期", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date taskStartDate;

    @Excel(name = "订单状态", width = 20)
    private String reorderState;

    @Excel(name = "发布人" , width = 20)
    private String releaseUserName;

    @Override
    public String getOrderCode() {
        return orderCode;
    }

    @Override
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getRedemandType() {
        return redemandType;
    }

    public void setRedemandType(String redemandType) {
        this.redemandType = redemandType;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitleKeyword() {
        return titleKeyword;
    }

    public void setTitleKeyword(String titleKeyword) {
        this.titleKeyword = titleKeyword;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public Integer getOrderNum() {
        return orderNum;
    }

    @Override
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public Integer getFrequency() {
        return frequency;
    }

    @Override
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public Integer getCycle() {
        return cycle;
    }

    @Override
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    @Override
    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    @Override
    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
    }

    public BigDecimal getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(BigDecimal countPrice) {
        this.countPrice = countPrice;
    }

    @Override
    public Date getTaskStartDate() {
        return taskStartDate;
    }

    @Override
    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }


    public String getReorderState() {
        return reorderState;
    }

    public void setReorderState(String reorderState) {
        this.reorderState = reorderState;
    }

    public String getReleaseUserName() {
        return releaseUserName;
    }

    public void setReleaseUserName(String releaseUserName) {
        this.releaseUserName = releaseUserName;
    }
}
