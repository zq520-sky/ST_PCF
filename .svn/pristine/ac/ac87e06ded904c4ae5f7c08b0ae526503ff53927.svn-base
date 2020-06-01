package com.samton.web.manage.cust.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.cust.bean.entity.TCustProduct;

import java.io.Serializable;

/**
 * @Description: 卖家产品管理Excel
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 10:55
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class CustProductEntity extends TCustProduct implements Serializable {

    @Excel(name = "客户编号" ,width = 15)
    private String custCode;

    @Excel(name = "客户名称" ,width = 15)
    private String custName;

    @Excel(name = "电商平台" ,width = 20)
    private String platformType;

    @Excel(name = "卖家账号(店铺名称)" ,width = 20)
    private String accountsName;

    @Excel(name = "产品ID" ,width = 20)
    private String itemId;

    @Excel(name = "标题(关键词)" ,width = 40)
    private String titleKeyword;

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Override
    public String getPlatformType() {
        return platformType;
    }

    @Override
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
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
    public String getTitleKeyword() {
        return titleKeyword;
    }

    @Override
    public void setTitleKeyword(String titleKeyword) {
        this.titleKeyword = titleKeyword;
    }

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
}
