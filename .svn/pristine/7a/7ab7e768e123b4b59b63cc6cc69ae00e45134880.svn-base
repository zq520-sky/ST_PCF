package com.samton.web.manage.task.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.task.bean.entity.TCustTask;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 任务报告excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 15:25
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustTaskReportEntity extends TCustTask {

    @Excel(name = "订单编号", width = 40)
    private String taskCode;

    @Excel(name = "客户名称", width = 20)
    private String custName;

    @Excel(name = "电商平台", width = 20)
    private String platformType;

    @Excel(name = "需求类型", width = 20)
    private String demandTypeCn;

    @Excel(name = "买家账号代码", width = 20)
    private String buyerCode;

    @Excel(name = "买家账户名称", width = 20)
    private String buyerName;

    @Excel(name = "卖家账号", width = 20)
    private String accountsName;

    @Excel(name = "产品ID", width = 20)
    private String itemId;

    @Excel(name = "订单数量", width = 20)
    private Integer orderNum;

    @Excel(name = "商品单价($)", width = 20)
    private BigDecimal unitPrice;

    @Excel(name = "税收($)", width = 20)
    private BigDecimal tax;

    @Excel(name = "订单金额(元)", width = 20)
    private BigDecimal orderMoney;

    @Excel(name = "执行日期",format = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date execDate;

    @Excel(name = "任务状态", width = 20)
    private String taskStateCn;

    @Excel(name = "报告时间",format = "yyyy-MM-dd HH:mm:ss", width = 20)
    private Date taskFinishTime;

    @Excel(name = "负责人" , width = 20)
    private String liableUserName;

    @Excel(name = "完成时间状态", width = 20)
    private String taskFinishNote;

    @Override
    public String getTaskCode() {
        return taskCode;
    }

    @Override
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Override
    public String getAccountsName() {
        return accountsName;
    }

    @Override
    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public void setItemId(String itemId) {
        this.itemId = itemId;
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
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    @Override
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Override
    public Date getExecDate() {
        return execDate;
    }

    @Override
    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    @Override
    public Date getTaskFinishTime() {
        return taskFinishTime;
    }

    @Override
    public void setTaskFinishTime(Date taskFinishTime) {
        this.taskFinishTime = taskFinishTime;
    }

    public String getDemandTypeCn() {
        return demandTypeCn;
    }

    public void setDemandTypeCn(String demandTypeCn) {
        this.demandTypeCn = demandTypeCn;
    }

    public String getTaskStateCn() {
        return taskStateCn;
    }

    public void setTaskStateCn(String taskStateCn) {
        this.taskStateCn = taskStateCn;
    }

    public String getLiableUserName() {
        return liableUserName;
    }

    public void setLiableUserName(String liableUserName) {
        this.liableUserName = liableUserName;
    }

    @Override
    public String getTaskFinishNote() {
        return taskFinishNote;
    }

    @Override
    public void setTaskFinishNote(String taskFinishNote) {
        this.taskFinishNote = taskFinishNote;
    }
}
