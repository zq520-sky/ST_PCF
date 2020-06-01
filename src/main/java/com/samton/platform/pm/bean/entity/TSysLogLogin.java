package com.samton.platform.pm.bean.entity;

import com.samton.platform.framework.base.BaseBean;
import com.samton.platform.framework.util.CurrentUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description:登录日志实体
 * @author:     shenchu
 * @date:        2017年2月23日 下午5:12:36
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class TSysLogLogin extends BaseBean implements Serializable {
    //登录日志ID
	private Long logId;
	//登录时间
    private Date loginDate;
    //登录IP
    private String loginIp;
    //登录描述
    private String loginDesc;

    private String loginName;
    
    private String userName;
    //系统权限类型
    private Integer sysType;
    //代理商ID
    private Integer proxyId;
    //客户ID
    private Integer custId;
    
    private Date startDateTime;
    
    private Date endDateTime;

    private static final long serialVersionUID = 1L;

    public TSysLogLogin() {}
    
    public TSysLogLogin(Short logType, String loginDesc, String loginIp){
		this.loginDesc = loginDesc;
		this.loginIp = loginIp;
    	this.createUserId = CurrentUtil.getCurrentUser().getUserId();
    	this.modifyUserId = CurrentUtil.getCurrentUser().getUserId();
    	this.createUserName = CurrentUtil.getCurrentUser().getUserName();
    	this.modifyUserName = CurrentUtil.getCurrentUser().getUserName();
    	Date nowDate=new Date();
    	this.setCreateDate(nowDate);
    	this.setLoginDate(nowDate);
	}
    
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDesc() {
        return loginDesc;
    }

    public void setLoginDesc(String loginDesc) {
        this.loginDesc = loginDesc;
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getProxyId() {
        return proxyId;
    }

    public void setProxyId(Integer proxyId) {
        this.proxyId = proxyId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }
}