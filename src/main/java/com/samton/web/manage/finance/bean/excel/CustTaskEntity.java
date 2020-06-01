package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.task.bean.entity.TCustTask;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户消费记录Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 11:51
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustTaskEntity extends TCustTask {

    @Excel(name = "客户名称",width = 30)
    private String custName;

    @Excel(name = "账号(店铺名称)",width = 30)
    private String accountsName;

    @Excel(name = "电商平台",width = 30)
    private String platformType;

    @Excel(name = "需求类型",width = 30)
    private String redemandType;

    //还款状态
    @Excel(name = "还款状态",width = 30)
    private Integer isClose;

    @Excel(name = "产品ID",width = 30)
    private String itemId;

    @Excel(name = "订单数量(元)",width = 30)
    private Integer orderNum;

    @Excel(name = "商品单价($)",width = 30)
    private BigDecimal unitPrice;

    @Excel(name = "税收($)",width = 30)
    private BigDecimal tax;

    @Excel(name = "美元汇率",width = 30)
    private BigDecimal dollarRate;

    @Excel(name = "订单金额",width = 30)
    private BigDecimal orderMoney;

    @Excel(name = "任务完成时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date taskFinishTime;


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Override
    public String getAccountsName() {
        return accountsName;
    }

    @Override
    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getRedemandType() {
        return redemandType;
    }

    public void setRedemandType(String redemandType) {
        this.redemandType = redemandType;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
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
    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
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
    public Date getTaskFinishTime() {
        return taskFinishTime;
    }

    @Override
    public void setTaskFinishTime(Date taskFinishTime) {
        this.taskFinishTime = taskFinishTime;
    }
}
