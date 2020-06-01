package com.samton.web.manage.finance.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.finance.bean.entity.TCustDepositOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CustDepositOrderEntity extends TCustDepositOrder implements Serializable {

    @Excel(name = "客户编号", width = 15)
    private String custCode;

    @Excel(name = "客户名称", width = 15)
    private String custName;

    @Excel(name = "所属公司", width = 40)
    private String companyName;

    @Excel(name = "操作前账户余额(元)", width = 20)
    private BigDecimal beforeAccountMoney;

    @Excel(name = "操作后账户余额(元)", width = 20)
    private BigDecimal afterAccountMoney;

    @Excel(name = "充值金额(元)", numFormat = "", width = 20)
    private BigDecimal rechargeMoney;

    @Excel(name = "充值方式", width = 15)
    private String repayType;

    @Excel(name = "充值时间", format = "yyyy-MM-dd HH:mm:ss",  width = 25)
    private Date payTime;

    @Excel(name = "备注", width = 60)
    private String remark;

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

    public BigDecimal getBeforeAccountMoney() {
        return beforeAccountMoney;
    }

    public void setBeforeAccountMoney(BigDecimal beforeAccountMoney) {
        this.beforeAccountMoney = beforeAccountMoney;
    }

    public BigDecimal getAfterAccountMoney() {
        return afterAccountMoney;
    }

    public void setAfterAccountMoney(BigDecimal afterAccountMoney) {
        this.afterAccountMoney = afterAccountMoney;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
