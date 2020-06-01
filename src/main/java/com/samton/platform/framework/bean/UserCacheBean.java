package com.samton.platform.framework.bean;

import com.samton.platform.pm.bean.entity.TSysPmMenu;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description:登录用户缓存类
 * @author:     Alex
 * @date:        2017年2月22日 下午1:37:53
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class UserCacheBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 用户ID
	protected Long userId;
	// 登录名称
	protected String loginName;
	// 系统ID
	protected Integer systemId;
	// 企业ID
	protected Integer enterpriseId;
	// 用户名称
	protected String userName;
	// SessionId
	private String sessionId;
	// 登录IP
	private String ip;
	// 端口
	private int port;
	// 是否是admin用户
	private Short isAdmin;
	// 是否是管理员
	private Integer isManager;
	// 代理商ID
	private Integer proxyId;
	
	private List<TSysPmMenu> menus;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getProxyId() {
		return proxyId;
	}

	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Short getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Short isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public List<TSysPmMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<TSysPmMenu> menus) {
		this.menus = menus;
	}
}