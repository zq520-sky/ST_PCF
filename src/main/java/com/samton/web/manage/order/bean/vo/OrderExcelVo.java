package com.samton.web.manage.order.bean.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: OrderExcelVo
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-21 17:56
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public class OrderExcelVo {

    @Excel(name = "需求类型")
    private String demandType;

    @Excel(name = "平台")
    private String platformType;

    @Excel(name = "客户")
    private String custName;

    @Excel(name = "账号")
    private String accountsName;

    @Excel(name = "Item ID/ASIN")
    private String itemId;

    @Excel(name = "标题/关键词")
    private String titleKeyword;

    @Excel(name = "地址")
    private String address;

    @Excel(name = "订单数量")
    private Integer orderNum;

    @Excel(name = "单价")
    private BigDecimal unitPrice;

    @Excel(name = "刷单频次")
    private Integer frequency;

    @Excel(name = "刷单周期")
    private Integer cycle;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "任务开始日期", format = "yyyy/MM/dd")
    private Date taskStartDate;

    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }
}