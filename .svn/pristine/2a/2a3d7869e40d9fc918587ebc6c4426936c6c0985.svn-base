package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.finance.bean.entity.TCustAccountReturn;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 客户还款Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 11:43
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustAccountReturnEntity extends TCustAccountReturn {

    @Excel(name = "客户编号", width = 20)
    private String custCode;

    @Excel(name = "客户名称", width = 20)
    private String custName;

    @Excel(name = "所属公司", width = 30)
    private String companyName;

    @Excel(name = "还款金额(元)", width = 20)
    private BigDecimal returnMoney;

    @Excel(name = "备注", width = 40)
    private String remark;

    @Excel(name = "还款时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date returnTime;

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
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    @Override
    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    @Override
    public Date getReturnTime() {
        return returnTime;
    }

    @Override
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
