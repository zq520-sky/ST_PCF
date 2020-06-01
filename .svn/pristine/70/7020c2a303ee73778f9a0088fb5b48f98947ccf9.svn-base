package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.finance.bean.entity.TCustDepositOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户充值申请
 * @Author: ZhongShengbin
 * @Date: 2020/05/08 09:11
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustRechargeApplicationEntity extends TCustDepositOrder implements Serializable {

    @Excel(name = "充值订单编号", width = 30)
    private String orderCode;

    @Excel(name = "客户编号", width = 15)
    private String custCode;

    @Excel(name = "客户名称", width = 15)
    private String custName;

    @Excel(name = "所属公司", width = 40)
    private String companyName;

    @Excel(name = "充值金额(元)", numFormat = "", width = 20)
    private BigDecimal rechargeMoney;

    @Excel(name = "付款凭证", width = 30)
    private String payImage;

    @Excel(name = "充值方式", width = 15)
    private String repayType;

    @Excel(name = "充值时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date payTime;

    @Excel(name = "审核状态", width = 15)
    private String approveStateSn;

    @Excel(name = "备注", width = 60)
    private String remark;

    @Override
    public String getOrderCode() {
        return orderCode;
    }

    @Override
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

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
    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    @Override
    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    @Override
    public String getPayImage() {
        return payImage;
    }

    @Override
    public void setPayImage(String payImage) {
        this.payImage = payImage;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    @Override
    public Date getPayTime() {
        return payTime;
    }

    @Override
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getApproveStateSn() {
        return approveStateSn;
    }

    public void setApproveStateSn(String approveStateSn) {
        this.approveStateSn = approveStateSn;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
