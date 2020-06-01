package com.samton.web.manage.finance.bean.vo;

import com.samton.web.manage.finance.bean.entity.TCustAccount;

import java.math.BigDecimal;

/**
 * @Description: 客户账户实体扩展类
 * @Author: YangYangen
 * @Date: 2020/4/15 14:42
 * Copyright (c) 2020, Samton. All rights reserved
*/
public class CustAccountVo extends TCustAccount {
    private Integer userId;
    //操作类型1-充值、2-授信、3-调整汇率、4-还款
    private Integer oprType;
    private BigDecimal oprMoney;
    private String remark;
    //客户名称
    private String custCode;
    private String custName;
    private String companyName;
    //审核人员名字
    private String approveUserName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOprType() {
        return oprType;
    }

    public void setOprType(Integer oprType) {
        this.oprType = oprType;
    }

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

    public BigDecimal getOprMoney() {
        return oprMoney;
    }

    public void setOprMoney(BigDecimal oprMoney) {
        this.oprMoney = oprMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApproveUserName() {
        return approveUserName;
    }

    public void setApproveUserName(String approveUserName) {
        this.approveUserName = approveUserName;
    }
}
