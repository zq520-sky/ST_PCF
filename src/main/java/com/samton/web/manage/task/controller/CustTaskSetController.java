package com.samton.web.manage.task.controller;

import com.samton.platform.framework.base.BaseController;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.IDepartService;
import com.samton.platform.pm.service.ILogService;
import com.samton.web.manage.cust.constant.CustomerExpCodeConstant;
import com.samton.web.manage.task.bean.entity.TCustTaskSet;
import com.samton.web.manage.task.service.ICustTaskSetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.resource.spi.RetryableUnavailableException;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;
import java.util.Map;
/**
 * @Description: 任务分配设置Controller
 * @Author: YangYangen
 * @Date: 2020/4/17 13:50
 * Copyright (c) 2020, Samton. All rights reserved
*/
@Controller
@RequestMapping("/manage/task/")
public class CustTaskSetController extends BaseController {
    @Resource
    private ICustTaskSetService custTaskSetService;
    @Resource
    private ILogService logService;
    @Resource
    // 部门管理业务层
    public IDepartService departService;

    /**
     * 任务分配设置查看
     * @param taskSetId
     * @return
     * @throws Exception
     */
    @RequestMapping("viewCustTaskSet" + WebConstant.PAGE_SUFFIX)
    public String viewCustTaskSet(Integer taskSetId) throws Exception {
        Integer id = taskSetId == null ? 1 : taskSetId;
        TCustTaskSet custTaskSet = custTaskSetService.selectById(id);
        this.addAttr("custTaskSet", custTaskSet);
        return "task/taskSetManage";
    }

    /**
     * 任务分配设置编辑
     * @param custTaskSet
     * @return
     * @throws Exception
     */
    @RequestMapping("editCustTaskSet" + WebConstant.BUSINESS_SUFFIX)
    @ResponseBody
    public Map<String, Object> editCustTaskSet(TCustTaskSet custTaskSet) throws Exception {
        //校验主键ID
        if(custTaskSet.getTaskSetId() == null){
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        if(custTaskSet.getBuyerOprNum()==null){
            custTaskSet.setBuyerOprNum(0);
        }
        //执行编辑
        boolean result = custTaskSetService.updateSelectiveById(custTaskSet);
        if (result) {
            logService.addLog(new TSysLog("分配规则设置-编辑", "分配规则设置成功 ！", PmStateConstant.LOG_PLATFORM));
        } else {
            logService.addLog(new TSysLog("分配规则设置-编辑", "分配规则设置失败！", PmStateConstant.LOG_PLATFORM));
        }
        if (!result) {
            throw new ServiceException(CustomerExpCodeConstant.CUST_EDIT_COLUMN_ERROR);
        }
        return this.getResultMap(true, result);
    }

    /**
     * 任务设定
     * oprType 1：工作日分配比例设定 2：周末负责人
     * @return
     * @throws Exception
     */
    @RequestMapping("taskSet" + WebConstant.NO_AUTH_SUFFIX)
    public String taskSet() throws Exception {
        //oprType 1：工作日分配比例设定 2：周末负责人
        Integer oprType = this.getInt("oprType");
        TCustTaskSet custTaskSet = custTaskSetService.selectById(1);
        this.addAttr("custTaskSet", custTaskSet);
        Integer sysType = CurrentUtil.getCurrentUser().getSystemId();
        Integer proxyId = CurrentUtil.getCurrentUser().getProxyId();
        Integer custId = CurrentUtil.getCurrentUser().getEnterpriseId();
        List<TSysPmUser> userList = departService.selectUserList(sysType, proxyId, custId);
        this.addAttr("userList", userList);
        String reStr = "task/taskRateSet" ;
        if(2==oprType){
            reStr = "task/taskWeekendSet" ;
        }
        return reStr;
    }

}
