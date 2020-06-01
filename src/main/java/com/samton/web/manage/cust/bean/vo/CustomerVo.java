package com.samton.web.manage.cust.bean.vo;

import com.samton.web.manage.cust.bean.entity.TCustomer;

import java.math.BigDecimal;

/**
 * @Description: 客户信息扩展实体
 * @Author: YangYangen
 * @Date: 2020/4/13 10:02
 * Copyright (c) 2020, Samton. All rights reserved
*/
public class CustomerVo extends TCustomer {
    private Long userId;
    private String loginName;
    private BigDecimal dollarRate;
    private String isForbidCn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public BigDecimal getDollarRate() {
        return dollarRate;
    }

    public void setDollarRate(BigDecimal dollarRate) {
        this.dollarRate = dollarRate;
    }

    public String getIsForbidCn() {
        return isForbidCn;
    }

    public void setIsForbidCn(String isForbidCn) {
        this.isForbidCn = isForbidCn;
    }
}
