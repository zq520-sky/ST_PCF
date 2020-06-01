package com.samton.platform.framework.interceptor;

import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.constant.AppConstant;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.FilterException;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysPmMenu;
import com.samton.platform.pm.constant.PmExpCodeConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description: 拦截url地址，判断地址是否有效
 * @Author: YangYangen
 * @Date: 2020/2/2 15:23
 * Copyright (c) 2019, Samton. All rights reserved
*/
@SuppressWarnings("rawtypes")
public class SysInterceptor implements HandlerInterceptor{

	/**
	 * 方法调用前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		//当前访问链接
		String doURL = request.getServletPath();
		int index=doURL.indexOf(".");
		//没有后缀的访问路径，视为无效路径
		if(!doURL.contains("/api/") && index==-1){
			throw new FilterException(PmExpCodeConstant.PM_URL_INVALID,null);
		}


		if(doURL.endsWith(WebConstant.PAGE_SUFFIX )|| doURL.endsWith(WebConstant.BUSINESS_SUFFIX)){
			HttpSession session=request.getSession(true);
			//控制session过期退回登录页
			UserCacheBean user = (UserCacheBean) session.getAttribute(WebConstant.USER_SESSION);
			if (user==null){
				response.sendRedirect("/resources/platform/views/login.jsp"	);
				return false;

			}
			Map attribute = (Map) request.getSession().getServletContext().getAttribute(AppConstant.USERSESSIONMAP);
			String userID = String.valueOf(user.getUserId());
			Map userSess = (Map) attribute.get(userID);
			String sessionId = (String) userSess.get(userID);
			//控制同一账户只能在一个地方登录
			if(!request.getSession().getId().equals(sessionId) && StringUtils.isNotBlank(sessionId)){
				//被迫下线！该帐户在另一个地方登录。
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("<script>alert('被迫下线！该帐户在另一个地方登录。'); window.location = '"+request.getContextPath()+"/';</script>");
				//response.sendRedirect("/resources/platform/views/login.jsp"	);
				return false;
			}
			UserCacheBean userCacheBean=CurrentUtil.getCurrentUser();
			List<TSysPmMenu> pmMenus= userCacheBean.getMenus();
			boolean auth_flag = false;
			//超级管理员无需权限拦截
			if(userCacheBean.getUserId() == 1) auth_flag = true;
			//个人设置：/platform/pm/updateUser
			//修改密码：/platform/pm/resetUserPwd
			//富文本图片上传 uploadFile.json
			if("/platform/pm/updateUser.json".equals(doURL) || "/platform/pm/changePwd.json".equals(doURL)
					|| doURL.contains("uploadFile.json")
			){
				auth_flag = true;
			}
			if(!auth_flag){
				//权限路径
				for (Iterator iterator = pmMenus.iterator(); iterator.hasNext();) {
					TSysPmMenu tSysPmMenu = (TSysPmMenu) iterator.next();

					if(doURL.equals(tSysPmMenu.getMenuUrl())){
						auth_flag = true;
						break;
					}
				}
			}
			if(!auth_flag){
				throw new FilterException(PmExpCodeConstant.PM_NO_AUTH,null);
			}
		}

		return true;
	}

	/**
	 * preHandle返回true后，视图渲染前执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * preHandle返回true后，视图渲染后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}



}
