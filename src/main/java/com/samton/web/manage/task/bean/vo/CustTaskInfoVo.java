package com.samton.web.manage.task.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samton.web.manage.buyer.bean.entity.TBuyers;
import com.samton.web.manage.cust.bean.entity.TCustProduct;
import com.samton.web.manage.cust.bean.entity.TCustomer;
import com.samton.web.manage.finance.bean.entity.TCustAccount;
import com.samton.web.manage.order.bean.entity.TCustOrder;
import com.samton.web.manage.task.bean.entity.TCustTask;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**
 * @Description: 任务信息报告扩展实体
 * @Author: YangYangen
 * @Date: 2020/4/18 15:05
 * Copyright (c) 2020, Samton. All rights reserved
*/
public class CustTaskInfoVo {
    //任务信息
    private TCustTask task;
    //客户产品
    private TCustProduct product;
    //客户信息
    private TCustomer cust;
    //客户账户
    private TCustAccount account;
    //买家信息
    private TBuyers buyer;
    //订单信息
    private TCustOrder order;

    //模糊查询label
    private String label;

    //模糊查询value
    private String value;

    //条件查询
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date execDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date execDateEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskFinishTimeBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskFinishTimeEnd;
    private Integer isRefund;

    //转义信息
    private String demandTypeCn;
    private String orderStateCn;
    private String taskStateCn;
    private String orderRemark;
    private String liableUserName;
    private String buyerAddress;
    private String taskRemark;
    private String taskFinishNote;

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    public TCustTask getTask() {
        return task;
    }

    public void setTask(TCustTask task) {
        this.task = task;
    }

    public TCustProduct getProduct() {
        return product;
    }

    public void setProduct(TCustProduct product) {
        this.product = product;
    }

    public TCustomer getCust() {
        return cust;
    }

    public void setCust(TCustomer cust) {
        this.cust = cust;
    }

    public TCustAccount getAccount() {
        return account;
    }

    public void setAccount(TCustAccount account) {
        this.account = account;
    }

    public TBuyers getBuyer() {
        return buyer;
    }

    public void setBuyer(TBuyers buyer) {
        this.buyer = buyer;
    }

    public TCustOrder getOrder() {
        return order;
    }

    public void setOrder(TCustOrder order) {
        this.order = order;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getExecDateBegin() {
        return execDateBegin;
    }

    public void setExecDateBegin(Date execDateBegin) {
        this.execDateBegin = execDateBegin;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getExecDateEnd() {
        return execDateEnd;
    }

    public void setExecDateEnd(Date execDateEnd) {
        this.execDateEnd = execDateEnd;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTaskFinishTimeBegin() {
        return taskFinishTimeBegin;
    }

    public void setTaskFinishTimeBegin(Date taskFinishTimeBegin) {
        this.taskFinishTimeBegin = taskFinishTimeBegin;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getTaskFinishTimeEnd() {
        return taskFinishTimeEnd;
    }

    public void setTaskFinishTimeEnd(Date taskFinishTimeEnd) {
        this.taskFinishTimeEnd = taskFinishTimeEnd;
    }

    public String getDemandTypeCn() {
        return demandTypeCn;
    }

    public void setDemandTypeCn(String demandTypeCn) {
        this.demandTypeCn = demandTypeCn;
    }

    public String getOrderStateCn() {
        return orderStateCn;
    }

    public void setOrderStateCn(String orderStateCn) {
        this.orderStateCn = orderStateCn;
    }

    public String getTaskStateCn() {
        return taskStateCn;
    }

    public void setTaskStateCn(String taskStateCn) {
        this.taskStateCn = taskStateCn;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getLiableUserName() {
        return liableUserName;
    }

    public void setLiableUserName(String liableUserName) {
        this.liableUserName = liableUserName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }

    public String getTaskFinishNote() {
        return taskFinishNote;
    }

    public void setTaskFinishNote(String taskFinishNote) {
        this.taskFinishNote = taskFinishNote;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
