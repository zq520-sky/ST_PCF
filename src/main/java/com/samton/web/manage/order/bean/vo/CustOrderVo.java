package com.samton.web.manage.order.bean.vo;

import com.samton.web.manage.order.bean.entity.TCustOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: CustOrderVo
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-21 16:12
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public class CustOrderVo extends TCustOrder {

    private String custName;
    private String custCode;
    private String accountsName;
    private String itemId;
    private String titleKeyword;

    /**发布日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseTimeEnd;

    /**任务开始日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskStartDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskStartDateEnd;

    /**发布人*/
    private String releaseUserName;

    /**客户名称或订单编号*/
    private String custNameOrOrderCode;

    private String platformType;

    private String orderStateCN;

    private String demandTypeCN;


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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

    public Date getReleaseTimeBegin() {
        return releaseTimeBegin;
    }

    public void setReleaseTimeBegin(Date releaseTimeBegin) {
        this.releaseTimeBegin = releaseTimeBegin;
    }

    public Date getReleaseTimeEnd() {
        return releaseTimeEnd;
    }

    public void setReleaseTimeEnd(Date releaseTimeEnd) {
        this.releaseTimeEnd = releaseTimeEnd;
    }

    public Date getTaskStartDateBegin() {
        return taskStartDateBegin;
    }

    public void setTaskStartDateBegin(Date taskStartDateBegin) {
        this.taskStartDateBegin = taskStartDateBegin;
    }

    public Date getTaskStartDateEnd() {
        return taskStartDateEnd;
    }

    public void setTaskStartDateEnd(Date taskStartDateEnd) {
        this.taskStartDateEnd = taskStartDateEnd;
    }

    public String getReleaseUserName() {
        return releaseUserName;
    }

    public void setReleaseUserName(String releaseUserName) {
        this.releaseUserName = releaseUserName;
    }

    public String getCustNameOrOrderCode() {
        return custNameOrOrderCode;
    }

    public void setCustNameOrOrderCode(String custNameOrOrderCode) {
        this.custNameOrOrderCode = custNameOrOrderCode;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getOrderStateCN() {
        return orderStateCN;
    }

    public void setOrderStateCN(String orderStateCN) {
        this.orderStateCN = orderStateCN;
    }

    public String getDemandTypeCN() {
        return demandTypeCN;
    }

    public void setDemandTypeCN(String demandTypeCN) {
        this.demandTypeCN = demandTypeCN;
    }
}