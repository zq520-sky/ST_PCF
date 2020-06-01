package com.samton.web.manage.cust.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description: 客户信息实体
 * @Author: YangYangen
 * @Date: 2020/4/13 10:01
 * Copyright (c) 2020, Samton. All rights reserved
*/
public class TCustomer extends BaseBean implements Serializable {
    private Integer custId;

    private String custCode;

    private String custName;

    private String companyName;

    private String contactTel;

    private String email;

    private Integer isForbid;

    private Long createUserId;

    private Date createDate;

    private Long modifyUserId;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
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

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(Integer isForbid) {
        this.isForbid = isForbid;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}