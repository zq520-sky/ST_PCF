package com.samton.web.manage.finance.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.finance.bean.excel.CustAccountReturnEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountReturnVo;
import com.samton.web.manage.finance.service.ICustAccountReturnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description: 还款记录控制类
 * @Author: ZhongShengbin
 * @Date: 2020/04/14 19:14
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Controller
@RequestMapping("/manage/finance")
public class CustAccountReturnController extends BaseController {

    @Resource
    ICustAccountReturnService iCustAccountReturnService;

    @Resource
    ILogService logService;

    private SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-01");
    private SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 客户授信记录分页
     *
     * @param paramBean
     * @param custAccountReturnVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryReturnInfoList" + WebConstant.PAGE_SUFFIX)
    public String queryReturnInfoList(Pagination<CustAccountReturnVo> paramBean, CustAccountReturnVo custAccountReturnVo) throws Exception {

        if (custAccountReturnVo.getReturnTimeBegin() == null) {
            custAccountReturnVo.setReturnTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custAccountReturnVo.getReturnTimeEnd() == null) {
            custAccountReturnVo.setReturnTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custAccountReturnVo);
        Pagination<CustAccountReturnVo> pageData = iCustAccountReturnService.queryReturnPageList(paramBean);
        this.addAttr("pageData", pageData);
        return "finance/custReturnManage";
    }
    /**
     * 客户授信记录导出
     * @param paramBean
     * @param custAccountReturnVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportReturnInfoList" + WebConstant.BUSINESS_SUFFIX)
    public String exportReturnInfoList(Pagination<CustAccountReturnVo> paramBean, CustAccountReturnVo custAccountReturnVo) throws Exception {
        if (custAccountReturnVo.getReturnTimeBegin() == null) {
            custAccountReturnVo.setReturnTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custAccountReturnVo.getReturnTimeEnd() == null) {
            custAccountReturnVo.setReturnTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custAccountReturnVo);
        Pagination<CustAccountReturnEntity> list = iCustAccountReturnService.exportReturnPageList(paramBean);
        logService.addLog(new TSysLog("客户还款记录-导出", "导出客户还款记录！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"客户还款记录" + String.format("%1$tY%1$tm%1$td", new Date()), CustAccountReturnEntity.class, list.getData());
        return null;
    }

    /**
     * 客户授信记录查看
     * @param returnId
     * @return
     * @throws Exception
     */
    @RequestMapping("/viewReturnInfoList" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewReturn(Integer returnId) throws Exception {
        CustAccountReturnVo custAccountReturnVo = iCustAccountReturnService.selectReturnVoById(returnId);
        return this.getResultMap(true, custAccountReturnVo);
    }


    /**
     * 客户授信记录分页（客户端）
     *
     * @param paramBean
     * @param custAccountReturnVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryReturnInfoListByCustId" + WebConstant.PAGE_SUFFIX)
    public String queryReturnInfoListByCustId(Pagination<CustAccountReturnVo> paramBean, CustAccountReturnVo custAccountReturnVo) throws Exception {

        if (custAccountReturnVo.getReturnTimeBegin() == null) {
            custAccountReturnVo.setReturnTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custAccountReturnVo.getReturnTimeEnd() == null) {
            custAccountReturnVo.setReturnTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        custAccountReturnVo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());
        paramBean.setSearch(custAccountReturnVo);
        Pagination<CustAccountReturnVo> pageData = iCustAccountReturnService.queryReturnPageList(paramBean);
        this.addAttr("pageData", pageData);
        return "finance/custReturnManageCust";
    }
    /**
     * 客户授信记录导出(客户端)
     * @param paramBean
     * @param custAccountReturnVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/exportReturnInfoListByCustId" + WebConstant.BUSINESS_SUFFIX)
    public String exportReturnInfoListByCustId(Pagination<CustAccountReturnVo> paramBean, CustAccountReturnVo custAccountReturnVo) throws Exception {
        custAccountReturnVo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());

        if (custAccountReturnVo.getReturnTimeBegin() == null) {
            custAccountReturnVo.setReturnTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custAccountReturnVo.getReturnTimeEnd() == null) {
            custAccountReturnVo.setReturnTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }
        paramBean.setSearch(custAccountReturnVo);
        Pagination<CustAccountReturnEntity> list = iCustAccountReturnService.exportReturnPageList(paramBean);
        logService.addLog(new TSysLog("客户还款记录-导出", "导出客户还款记录！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"客户还款记录(客户端)" + String.format("%1$tY%1$tm%1$td", new Date()), CustAccountReturnEntity.class, list.getData());
        return null;
    }

    /**
     * 客户授信记录查看（客户端）
     * @param returnId
     * @return
     * @throws Exception
     */
    @RequestMapping("/viewReturnInfoListByCustId" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewReturnByCustId(Integer returnId) throws Exception {
        CustAccountReturnVo custAccountReturnVo = iCustAccountReturnService.selectReturnVoById(returnId);
        return this.getResultMap(true, custAccountReturnVo);
    }

}
