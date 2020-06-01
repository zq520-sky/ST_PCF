package com.samton.web.manage.buyer.bean.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.samton.web.manage.buyer.bean.entity.TBuyers;

import java.math.BigDecimal;

/**
 * @Description: 买家账号管理
 * @Author: ZhongShengbin
 * @Date: 2020/05/07 17:02
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class BuyersRecordExcel extends TBuyers {

    @Excel(name = "电商平台",width = 20)
    private String platformType;

    @Excel(name = "账号代码",width = 20)
    private String buyerCode;

    @Excel(name = "账户名称",width = 20)
    private String buyerName;

    @Excel(name = "买家姓名",width = 20)
    private String userName;

    @Excel(name = "地址",width = 20)
    private String address;

    @Excel(name = "城市",width = 20)
    private String city;

    @Excel(name = "州",width = 20)
    private String province;

    @Excel(name = "邮编",width = 20)
    private String postcode;

    @Excel(name = "历史余额",width = 20)
    private BigDecimal accountMoneyHis;

    @Excel(name = "刷单充值",width = 20)
    private BigDecimal brushRecharge;

    @Excel(name = "采购充值",width = 20)
    private BigDecimal purchaseRecharge;

    @Excel(name = "充值总额",width = 20)
    private BigDecimal rechargeTotal;

    @Excel(name = "单月可用额度",width = 20)
    private BigDecimal quotaMonth;

    @Excel(name = "刷单次数",width = 20)
    private Integer brushNum;

    @Excel(name = "采购次数",width = 20)
    private Integer purchaseNum;

    @Excel(name = "刷单总额",width = 20)
    private BigDecimal brushTotalMoney;

    @Excel(name = "采购总额",width = 20)
    private BigDecimal purchaseTotalMoney;

    @Excel(name = "余额",width = 20)
    private BigDecimal accountMoney;

    @Excel(name = "禁用状态",width = 20)
    private String isForbidCN;

    @Override
    public String getPlatformType() {
        return platformType;
    }

    @Override
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    @Override
    public String getBuyerCode() {
        return buyerCode;
    }

    @Override
    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    @Override
    public String getBuyerName() {
        return buyerName;
    }

    @Override
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getProvince() {
        return province;
    }

    @Override
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String getPostcode() {
        return postcode;
    }

    @Override
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public BigDecimal getAccountMoneyHis() {
        return accountMoneyHis;
    }

    public void setAccountMoneyHis(BigDecimal accountMoneyHis) {
        this.accountMoneyHis = accountMoneyHis;
    }

    public BigDecimal getBrushRecharge() {
        return brushRecharge;
    }

    public void setBrushRecharge(BigDecimal brushRecharge) {
        this.brushRecharge = brushRecharge;
    }

    public BigDecimal getPurchaseRecharge() {
        return purchaseRecharge;
    }

    public void setPurchaseRecharge(BigDecimal purchaseRecharge) {
        this.purchaseRecharge = purchaseRecharge;
    }

    public BigDecimal getRechargeTotal() {
        return rechargeTotal;
    }

    public void setRechargeTotal(BigDecimal rechargeTotal) {
        this.rechargeTotal = rechargeTotal;
    }

    public BigDecimal getQuotaMonth() {
        return quotaMonth;
    }

    public void setQuotaMonth(BigDecimal quotaMonth) {
        this.quotaMonth = quotaMonth;
    }

    public Integer getBrushNum() {
        return brushNum;
    }

    public void setBrushNum(Integer brushNum) {
        this.brushNum = brushNum;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getBrushTotalMoney() {
        return brushTotalMoney;
    }

    public void setBrushTotalMoney(BigDecimal brushTotalMoney) {
        this.brushTotalMoney = brushTotalMoney;
    }

    public BigDecimal getPurchaseTotalMoney() {
        return purchaseTotalMoney;
    }

    public void setPurchaseTotalMoney(BigDecimal purchaseTotalMoney) {
        this.purchaseTotalMoney = purchaseTotalMoney;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getIsForbidCN() {
        return isForbidCN;
    }

    public void setIsForbidCN(String isForbidCN) {
        this.isForbidCN = isForbidCN;
    }
}
