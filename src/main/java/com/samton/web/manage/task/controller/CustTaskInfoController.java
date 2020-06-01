package com.samton.web.manage.task.controller;

import com.samton.platform.common.util.DateUtil;
import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.buyer.bean.entity.TBuyers;
import com.samton.web.manage.buyer.service.IBuyerAccountService;
import com.samton.web.manage.cust.constant.CustomerExpCodeConstant;
import com.samton.web.manage.task.bean.entity.TCustTask;
import com.samton.web.manage.task.bean.excel.CustTaskInfoEntity;
import com.samton.web.manage.task.bean.vo.CustTaskInfoVo;
import com.samton.web.manage.task.bean.vo.CustTaskVo;
import com.samton.web.manage.task.service.ICustTaskService;
import org.springframework.stereotype.Controller;
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
import java.util.List;
import java.util.Map;

/**
 * @Description: 任务信息Controller
 * @Author: YangYangen
 * @Date: 2020/4/18 17:11
 * Copyright (c) 2020, Samton. All rights reserved
*/
@Controller
@RequestMapping("/manage/task/")
public class CustTaskInfoController extends BaseController {
    @Resource
    private ICustTaskService iCustTaskService;
    @Resource
    private IBuyerAccountService buyerAccountService;
    @Resource
    private ILogService logService;

    private SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 任务信息分页查询
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustTaskInfoList" + WebConstant.PAGE_SUFFIX)
    public String queryCustTaskInfoList(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {
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

        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskInfoVo> pageData = iCustTaskService.queryPageCustTaskInfoList(paramBean);
        this.addAttr("pageData", pageData);
        return "task/custTaskInfoManage";
    }
    /**
     * 任务信息导出
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustTaskInfoList" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustTaskInfoList(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {
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
        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskInfoEntity> list = iCustTaskService.exportPageCustTaskInfoList(paramBean);
        logService.addLog(new TSysLog("任务信息-导出", "导出任务信息！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"任务信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), CustTaskInfoEntity.class, list.getData());
        return null;
    }

    /**
     * 任务信息查看
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustTaskInfo" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewCustTaskInfo(Integer taskId) throws Exception {
        CustTaskInfoVo custTaskInfoVo = iCustTaskService.findCustTaskInfoById(taskId);
        return this.getResultMap(true, custTaskInfoVo);
    }

    /**
     * 编辑任务信息
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("editCustTaskInfo" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editCustTaskInfo(CustTaskInfoVo custTaskInfoVo) throws Exception {
        //校验主键ID
        if(custTaskInfoVo.getTask() == null || custTaskInfoVo.getTask().getTaskId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        boolean result = iCustTaskService.updateSelectiveById(custTaskInfoVo.getTask());
        if (result) {
            logService.addLog(new TSysLog("客户任务信息-编辑", "编辑客户任务信息【" + custTaskInfoVo.getTask().getTaskCode() + "】成功！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户任务信息-编辑", "编辑客户任务信息【" + custTaskInfoVo.getTask().getTaskCode() + "】失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }

    /**
     * 转单
     * @param tCustTask
     * @param liableUserName
     * @return
     * @throws Exception
     */
    @RequestMapping("changeLiableUser" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> changeLiableUser(TCustTask tCustTask, String liableUserName) throws Exception {
        //校验主键ID
        if(tCustTask == null || tCustTask.getTaskId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        tCustTask.setModifyDate(new Date());
        tCustTask.setModifyUserId(CurrentUtil.getCurrentUser().getUserId());
        boolean result = iCustTaskService.updateSelectiveById(tCustTask);
        if (result) {
            logService.addLog(new TSysLog("客户任务信息-转单", "编辑客户任务信息，任务编号【" + tCustTask.getTaskCode() + "】转单给【"+liableUserName+"】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户任务信息-转单", "编辑客户任务信息，任务编号【" + tCustTask.getTaskCode() + "】转单给【"+liableUserName+"】失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }
    /**
     * 批量转单
     * @param taskIds
     * @param liableUserName
     * @return
     * @throws Exception
     */
    @RequestMapping("changeLiableUserList" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> changeLiableUserList(Integer[] taskIds, Integer liableUserId, String liableUserName) throws Exception {
        //校验主键ID
        if(taskIds == null || taskIds.length == 0){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        //执行编辑
        int result = iCustTaskService.updateLiableUserBatchById(taskIds, CurrentUtil.getCurrentUser().getUserId(), new Date(),  liableUserId);
        if (result > 0) {
            logService.addLog(new TSysLog("客户任务信息-批量转单", "编辑客户任务信息，批量转单给【"+liableUserName+"】成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("客户任务信息-批量转单", "编辑客户任务信息，批量转单失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (result <= 0) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }


    /**
     * 通过买家账号查询买家信息
     * @param buyerCode
     * @return
     * @throws Exception
     */
    @RequestMapping("viewBuyerbyBuyerCode" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewBuyerbyBuyerCode(String buyerCode) throws Exception {
        TBuyers buyer = buyerAccountService.findBuyerByBuyerCode(buyerCode);
        return this.getResultMap(true, buyer);
    }

    /**
     * 任务信息分页查询(客户端)
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("queryCustTaskInfoListByCustId" + WebConstant.PAGE_SUFFIX)
    public String queryCustTaskInfoListByCustId(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {
        CustTaskVo vo = new CustTaskVo();
        vo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());
        custTaskInfoVo.setTask(vo);
        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskInfoVo> pageData = iCustTaskService.queryPageCustTaskInfoList(paramBean);
        this.addAttr("pageData", pageData);
        return "task/custTaskInfoManageCust";
    }

    /**
     * 任务信息导出(客户端)
     * @param paramBean
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @RequestMapping("exportCustTaskInfoListByCustId" + WebConstant.BUSINESS_SUFFIX)
    public String exportCustTaskInfoListByCustId(Pagination<CustTaskInfoVo> paramBean, CustTaskInfoVo custTaskInfoVo) throws Exception {
        CustTaskVo vo = new CustTaskVo();
        vo.setCustId(CurrentUtil.getCurrentUser().getEnterpriseId());
        custTaskInfoVo.setTask(vo);
        paramBean.setSearch(custTaskInfoVo);
        Pagination<CustTaskInfoEntity> list = iCustTaskService.exportPageCustTaskInfoList(paramBean);
        logService.addLog(new TSysLog("任务信息-导出", "导出任务信息！", PmStateConstant.LOG_PLATFORM));
        this.export(response,"任务信息管理" + String.format("%1$tY%1$tm%1$td", new Date()), CustTaskInfoEntity.class, list.getData());
        return null;
    }

    /**
     * 任务信息查看(客户端)
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustTaskInfoByCustId" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> viewCustTaskInfoByCustId(Integer taskId) throws Exception {
        CustTaskInfoVo custTaskInfoVo = iCustTaskService.findCustTaskInfoById(taskId);
        return this.getResultMap(true, custTaskInfoVo);
    }

    /**
     * 导入任务
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("importTasks" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> importTasks(@RequestParam("file") CommonsMultipartFile file) throws Exception {
        Map<String, String> stringStringMap = iCustTaskService.importTasks(file);
        logService.addLog(new TSysLog("任务信息管理-导入任务", "导入任务成功 ！", PmStateConstant.LOG_PLATFORM));
        return this.getResultMap(true, stringStringMap);
    }

    /**
     * 导入任务模板下载
     * @param request
     * @param response
     */
    @RequestMapping("downloadTaskTemple" + WebConstant.NO_AUTH_SUFFIX)
    public void downloadTaskTemple(HttpServletRequest request, HttpServletResponse response) {
        try {
            String fileName = "任务模板.xlsx";
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
            tempFile = "templateDownload/custTask.xlsx";
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

    /**
     * @Author ZhongShengbin
     * @Description 任务信息编辑处依据账号代码模糊查询
     * @Date 2020/4/30 0030
     * @Param [buyerCode]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @RequestMapping("/queryBuyerCode"+WebConstant.NO_AUTH_SUFFIX)
    @ResponseBody
    public List<Map<String,Object>> queryBuyerCode(String buyerCode) throws Exception {
        List<Map<String,Object>> buyerList = iCustTaskService.queryBuyerCode(buyerCode);
        return buyerList;
    }

}
