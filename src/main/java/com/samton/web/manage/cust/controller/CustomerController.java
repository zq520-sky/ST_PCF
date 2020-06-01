package com.samton.web.manage.cust.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.platform.pm.service.IPmService;
import com.samton.platform.pm.util.RegisterUtil;
import com.samton.web.manage.cust.bean.entity.TCustomer;
import com.samton.web.manage.cust.bean.excel.CustomerEntity;
import com.samton.web.manage.cust.bean.vo.CustomerVo;
import com.samton.web.manage.cust.constant.CustomerExpCodeConstant;
import com.samton.web.manage.cust.service.ICustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 客户信息Controller
 * @Author: YangYangen
 * @Date: 2020/4/13 11:20
 * Copyright (c) 2020, Samton. All rights reserved
*/
@Controller
@RequestMapping("/manage/customer/")
public class CustomerController extends BaseController {

    @Resource
    private ICustomerService customerService;
    @Resource
    private IPmService pmService;
    @Resource
    private ILogService logService;

    /**
     * 客户信息分页
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustomerInfoList" + WebConstant.PAGE_SUFFIX)
    public String queryCustomerInfoList(Pagination<CustomerVo> paramBean, CustomerVo customerVo) throws Exception{
        paramBean.setSearch(customerVo);
        Pagination<CustomerVo> pageData = customerService.queryPageCustomerVoList(paramBean);
        this.addAttr("pageData", pageData);
        return "cust/custInfoManage";
    }

    /**
     * 客户信息管理导出
     * @param paramBean
     * @param customerVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustomerInfoList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustomerInfoList(Pagination<TCustomer> paramBean, CustomerVo customerVo) throws Exception {
        paramBean.setSearch(customerVo);
        Pagination<CustomerEntity> list = customerService.exportPageCustomerVoList(paramBean);
        logService.addLog(new TSysLog("客户信息-导出", "导出客户信息！", PmStateConstant.LOG_PLATFORM));
        this.export(response, "客户信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), CustomerEntity.class, list.getData());
        return null;
    }

    /**
     * 客户信息新增
     * @param tCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping("addCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> addCustomer(TCustomer tCustomer) throws Exception {
        if(StringUtils.isBlank(tCustomer.getContactTel())){
            throw new ServiceException(CustomerExpCodeConstant.CUST_ADD_COLUMN_ERROR);
        }
        //执行新增
        boolean result = customerService.insertCustomer(tCustomer);
        if (result) {
            logService.addLog(new TSysLog("客户信息管理-新增", "新增客户信息【" + tCustomer.getCustName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-新增", "新增客户信息【" + tCustomer.getCustName() + "】 失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_ADD_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }


    /**
     * 客户信息查看
     * @param custId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewCustomer(Integer custId) throws Exception {
        CustomerVo customerVo = customerService.findCustomerVoById(custId);

        return this.getResultMap(true, customerVo);
    }

    /**
     * 客户信息编辑
     * @param tCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping("editCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editCustomer(TCustomer tCustomer) throws Exception {
        //校验主键ID
        if(tCustomer.getCustId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean result = customerService.updateSelectiveById(tCustomer);
        if (result) {
            logService.addLog(new TSysLog("客户信息管理-编辑", "编辑客户信息【" + tCustomer.getCustName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-编辑", "编辑客户信息【" + tCustomer.getCustName() + "】 失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }

    /**
     * 客户信息禁用/启用
     * @param tCustomer
     * @return
     * @throws Exception
     */
    @RequestMapping("setCustomer" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> setCustomer(TCustomer tCustomer) throws Exception {
        //校验主键ID
        if(tCustomer.getCustId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean result = customerService.updateSelectiveById(tCustomer);
        String headStr = tCustomer.getIsForbid() == 0 ? "启用" : "禁用";
        if (result) {
            logService.addLog(new TSysLog("客户信息管理-禁用/启用", headStr+"客户【" + tCustomer.getCustName() + "】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-禁用/启用", headStr+"客户【" + tCustomer.getCustName() + "】 失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //启用/禁用所有客户登录账户
        if("启用".equals(headStr)){
            pmService.setCustUser(3, tCustomer.getCustId(), 1);
        }else if("禁用".equals(headStr)){
            pmService.setCustUser(3, tCustomer.getCustId(), 0);
        }

        return this.getResultMap(true, result);
    }

    /**
     * 重置客户密码
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("resetUserPwd"+WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> resetUserPwd(long userId, String loginName) throws Exception {
        String resetPwd = RegisterUtil.randomPassword(PmStateConstant.RESET_PWD_LEN);
        TSysPmUser user = new TSysPmUser();
        user.setUserId(userId);
        user.setPwd(resetPwd);
        boolean flag = pmService.updateUser(user);
        if (flag) {
            logService.addLog(new TSysLog("客户信息管理-重置密码", "重置客户【" + loginName + "】密码成功！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户信息管理-重置密码", "重置客户【" + loginName + "】密码失败！", PmStateConstant.LOG_PLATFORM));
        }
        return this.getResultMap(flag, resetPwd);
    }

    @RequestMapping("/getCustomerInfo"+WebConstant.NO_AUTH_SUFFIX)
    @ResponseBody
    public Object getCustomerInfo(Integer custId) throws Exception {
        Map<String, Object> result = customerService.selectCustAccountInfo(custId);
        return result;
    }


}
