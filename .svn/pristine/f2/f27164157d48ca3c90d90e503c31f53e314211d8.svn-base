package com.samton.web.manage.task.controller;

import com.samton.platform.common.util.DateUtil;
import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.cust.constant.CustomerExpCodeConstant;
import com.samton.web.manage.task.bean.entity.TCustTask;
import com.samton.web.manage.task.bean.excel.CustTaskReportEntity;
import com.samton.web.manage.task.bean.vo.CustTaskInfoVo;
import com.samton.web.manage.task.service.ICustTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 任务报告Controller
 * @Author: YangYangen
 * @Date: 2020/4/20 16:11
 * Copyright (c) 2020, Samton. All rights reserved
 */
@Controller
@RequestMapping("/manage/task/")
public class CustTaskReportController extends BaseController {
    @Resource
    private ICustTaskService iCustTaskService;
    @Resource
    private ILogService logService;

    private SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-01");
    private SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 任务报告分页查询
     *
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustTaskReportList" + WebConstant.PAGE_SUFFIX)
    public String queryCustTaskReportList(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {

        if (custTaskInfoVo.getExecDateBegin() == null) {
            custTaskInfoVo.setExecDateBegin(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 00:00:00"));
        }

        Date date = DateUtil.addDays(new Date(), 1);
        if (custTaskInfoVo.getExecDateEnd() == null) {
            custTaskInfoVo.setExecDateEnd(sdfParse.parse(sdfEnd.format(date.getTime()) + " 23:59:59"));
        }

        if(null == custTaskInfoVo.getTask()){
            TCustTask tCustTask = new TCustTask();
            tCustTask.setTaskState(0);
            custTaskInfoVo.setTask(tCustTask);
        }

        /*if (custTaskInfoVo.getTaskFinishTimeBegin() == null) {
            custTaskInfoVo.setTaskFinishTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custTaskInfoVo.getTaskFinishTimeEnd() == null) {
            custTaskInfoVo.setTaskFinishTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }*/
        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskInfoVo> pageData = iCustTaskService.queryPageCustTaskInfoList(paramBean);

        this.addAttr("pageData", pageData);
        return "task/custTaskReportManage";
    }

    /**
     * 任务报告导出
     *
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustTaskReportList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustTaskReportList(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {

        if (custTaskInfoVo.getExecDateBegin() == null) {
            custTaskInfoVo.setExecDateBegin(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 00:00:00"));
        }

        Date date = DateUtil.addDays(new Date(), 1);
        if (custTaskInfoVo.getExecDateEnd() == null) {
            custTaskInfoVo.setExecDateEnd(sdfParse.parse(sdfEnd.format(date.getTime()) + " 23:59:59"));
        }
        if(custTaskInfoVo.getTask().getTaskState() == null){
            custTaskInfoVo.getTask().setTaskState(0);
        }
        if(null == custTaskInfoVo.getTask()){
            TCustTask tCustTask = new TCustTask();
            tCustTask.setTaskState(0);
            custTaskInfoVo.setTask(tCustTask);
        }
        /*if (custTaskInfoVo.getTaskFinishTimeBegin() == null) {
            custTaskInfoVo.setTaskFinishTimeBegin(sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00"));
        }
        if (custTaskInfoVo.getTaskFinishTimeEnd() == null) {
            custTaskInfoVo.setTaskFinishTimeEnd(sdfParse.parse(sdfEnd.format(System.currentTimeMillis()) + " 23:59:59"));
        }*/
        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskReportEntity> list = iCustTaskService.exportPageCustTaskReportList(paramBean);
        logService.addLog(new TSysLog("任务报告-导出", "导出任务报告！", PmStateConstant.LOG_PLATFORM));
        this.export(response, "任务报告管理" + String.format("%1$tY%1$tm%1$td", new Date()), CustTaskReportEntity.class, list.getData());
        return null;
    }

    /**
     * 任务报告查看
     *
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustTaskReport" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewCustTaskReport(Integer taskId) throws Exception {
        CustTaskInfoVo custTaskInfoVo = iCustTaskService.findCustTaskInfoById(taskId);
        return this.getResultMap(true, custTaskInfoVo);
    }

    /**
     * 编辑任务报告
     *
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("editCustTaskReport" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editCustTaskReport(CustTaskInfoVo custTaskInfoVo) throws Exception {
        //校验主键ID
        if (custTaskInfoVo.getTask() == null || custTaskInfoVo.getTask().getTaskId() == null) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        String result = iCustTaskService.reportSettingOk(custTaskInfoVo);
        if ("".equals(result)) {
            logService.addLog(new TSysLog("客户任务报告-设置完成", "客户任务报告设置完成成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户任务报告-设置完成", "客户任务报告设置完成失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!"".equals(result)) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }

    /**
     * 编辑退款
     * @param custTaskInfoVo
     * @author wzq
     * @date 2020/5/15 10:11
     * @return {@link Map< String, Object>}
     */
    @RequestMapping("editRefund" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editRefund(CustTaskInfoVo custTaskInfoVo) throws Exception {
        //校验主键ID
        if (custTaskInfoVo.getTask() == null || custTaskInfoVo.getTask().getTaskId() == null) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean refund = iCustTaskService.editRefund(custTaskInfoVo);
        if (refund) {
            logService.addLog(new TSysLog("客户任务报告-设置退款", "客户任务报告设置退款成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户任务报告-设置退款", "客户任务报告设置退款失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!refund) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, refund);
    }

    /**
     * Excel xls格式 批量设置完成导入
     *@author: ZhongShengbin
     * @param file
     * @param type:1表示导入设置完成 2表示导入设置运单号模板
     * @return
     * @throws Exception
     */
    @RequestMapping("{type}/importReportSetting" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> importReportSetting(@RequestParam("file") CommonsMultipartFile file, @PathVariable("type") Integer type) throws Exception {
        //导入结果
        Map<String,String> stringStringMap = new HashMap();
        try {
            if (type == 1) {
                stringStringMap = iCustTaskService.importReportSettingOk(file);
            } else {
                stringStringMap = iCustTaskService.importReportSettingSn(file);
            }
        }catch (NullPointerException p){
            stringStringMap.put("error",p.getMessage());
        }
        String error = stringStringMap.get("error");
        String name = file.getOriginalFilename();
        TSysLog tSysLog = null;
        if (StringUtils.isBlank(error)) {
            tSysLog = new TSysLog("任务报告管理-批量设置任务完成状态", "导入设置状态【" + name + "】成功 ！", PmStateConstant.LOG_PLATFORM);
        } else {
            tSysLog = new TSysLog("任务报告管理-批量设置任务完成状态", "导入设置状态【" + name + "】失败 ！", PmStateConstant.LOG_PLATFORM);
        }
        logService.addLog(tSysLog);
        System.out.print(stringStringMap.get("error"));
        return this.getResultMap(true, stringStringMap);
    }

    /**
     * @param request:
     * @param response:
     * @param type:1表示设置完成模板 2表示设置运单号模板
     * @author: ZhongShengbin
     * @date: 2020/4/27
     * @return: void
     */
    @RequestMapping("/{type}/downloadTemple" + WebConstant.NO_AUTH_SUFFIX)
    public void downloadTemple(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") Integer type) {
        String fileName;
        try {
            if (type == 1) {
                fileName = "任务完成设置模板.xlsx";
            } else {
                fileName = "运单号导入模板.xlsx";
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            String agent = request.getHeader("USER-AGENT");
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            String tempFile = null;
            if (type == 1) {
                tempFile = "templateDownload/custTaskReportSettingOk.xlsx";
            } else {
                tempFile = "templateDownload/custTaskReportSettingSn.xlsx";
            }
            OutputStream outputStream = response.getOutputStream();
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(tempFile);
            byte[] data = new byte[1024];
            int read = 0;
            while ((read = resourceAsStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
            resourceAsStream.close();
            outputStream.close();
        } catch (Exception e) {

        }
    }
}
