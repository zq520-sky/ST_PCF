package com.samton.web.manage.finance.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.finance.bean.excel.CustAccountReturnEntity;
import com.samton.web.manage.finance.bean.excel.CustTaskEntity;
import com.samton.web.manage.task.bean.vo.CustTaskVo;
import com.samton.web.manage.task.service.ICustTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 消费记录控制类
 * @Author: ZhongShengbin
 * @Date: 2020/04/14 19:14
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Controller
@RequestMapping("/manage/finance")
public class TaskInfoController extends BaseController {

    @Resource
    ICustTaskService iCustTaskService;

    @Resource
    ILogService logService;

    private SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-01");
    private SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 客户消费记录分页
     *
     * @param paramBean
     * @param custTaskVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryTaskInfoList" + WebConstant.PAGE_SUFFIX)
    public String queryTaskInfoList(Pagination<CustTaskVo> paramBean, CustTaskVo custTaskVo) throws Exception {

        if (custTaskVo.getTaskEndDateBegin() == null) {
            custTaskVo.setTaskEndDateBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custTaskVo.getTaskEndDateEnd() == null) {
            custTaskVo.setTaskEndDateEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custTaskVo);
        Pagination<CustTaskVo> pageData = iCustTaskService.queryTaskPageList(paramBean);
        this.addAttr("pageData", pageData);
        return "finance/taskInfoManage";
    }
    /**
     * 客户消费记录导出
     * @param paramBean
     * @param custTaskVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportTaskInfoList" + WebConstant.BUSINESS_SUFFIX)
    public String exportTaskInfoList(Pagination<CustTaskVo> paramBean, CustTaskVo custTaskVo) throws Exception {

        if (custTaskVo.getTaskEndDateBegin() == null) {
            custTaskVo.setTaskEndDateBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00"));
        }
        if (custTaskVo.getTaskEndDateEnd() == null) {
            custTaskVo.setTaskEndDateEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59"));
        }
        paramBean.setSearch(custTaskVo);
        Pagination<CustTaskEntity> list = iCustTaskService.exportTaskPageList(paramBean);
        logService.addLog(new TSysLog("客户消费记录-导出", "导出客户消费记录！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"客户消费记录" + String.format("%1$tY%1$tm%1$td", new Date()), CustAccountReturnEntity.class, list.getData());
        return null;
    }
    /**
     * 消费记录查看
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping("/viewTaskInfoList" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewTaskInfoList(Integer taskId,Integer orderId) throws Exception {
        CustTaskVo custTaskVo = iCustTaskService.selectTaskVoById(taskId,orderId);
        return this.getResultMap(true, custTaskVo);
    }


    /**
     * 客户消费记录分页（客户端）
     * @param paramBean
     * @param custTaskVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryConsumptionInfoListByCustId" + WebConstant.PAGE_SUFFIX)
    public String queryConsumptionInfoListByCustId(Pagination<CustTaskVo> paramBean, CustTaskVo custTaskVo) throws Exception {
        custTaskVo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());

        if (custTaskVo.getTaskEndDateBegin() == null) {
            custTaskVo.setTaskEndDateBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custTaskVo.getTaskEndDateEnd() == null) {
            custTaskVo.setTaskEndDateEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custTaskVo);
        Pagination<CustTaskVo> pageData = iCustTaskService.queryTaskPageList(paramBean);
        this.addAttr("pageData", pageData);
        return "finance/custConsumptionManage";
    }
    /**
     * 客户消费记录导出（客户端）
     * @param paramBean
     * @param custTaskVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportConsumptionInfoListByCustId" + WebConstant.BUSINESS_SUFFIX)
    public String exportConsumptionInfoListByCustId(Pagination<CustTaskVo> paramBean, CustTaskVo custTaskVo) throws Exception {
        custTaskVo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());
        if (custTaskVo.getTaskEndDateBegin() == null) {
            custTaskVo.setTaskEndDateBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custTaskVo.getTaskEndDateEnd() == null) {
            custTaskVo.setTaskEndDateEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custTaskVo);
        Pagination<CustTaskEntity> list = iCustTaskService.exportTaskPageList(paramBean);
        logService.addLog(new TSysLog("客户消费记录-导出", "导出客户消费记录！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"客户消费记录" + String.format("%1$tY%1$tm%1$td", new Date()), CustAccountReturnEntity.class, list.getData());
        return null;
    }

    /**
     * 客户消费记录查看（客户端）
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping("/viewConsumptionInfoListByCustId" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewConsumptionByTaskId(Integer taskId,Integer orderId) throws Exception {
        CustTaskVo custTaskVo = iCustTaskService.selectTaskVoById(taskId,orderId);
        return this.getResultMap(true, custTaskVo);
    }
}
