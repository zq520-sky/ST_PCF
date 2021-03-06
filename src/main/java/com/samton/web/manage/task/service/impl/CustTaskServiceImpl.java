package com.samton.web.manage.task.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.common.util.DateUtil;
import com.samton.platform.common.util.IdGen;
import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.JsonUtil;
import com.samton.platform.pm.bean.entity.TSysPmUser;
import com.samton.platform.pm.dao.TSysPmUserMapper;
import com.samton.web.manage.buyer.bean.entity.TBuyers;
import com.samton.web.manage.buyer.dao.TBuyersMapper;
import com.samton.web.manage.cust.bean.entity.TCustProduct;
import com.samton.web.manage.cust.dao.TCustProductMapper;
import com.samton.web.manage.finance.bean.excel.CustTaskEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountVo;
import com.samton.web.manage.finance.dao.TCustAccountMapper;
import com.samton.web.manage.order.bean.entity.TCustOrder;
import com.samton.web.manage.order.dao.TCustOrderMapper;
import com.samton.web.manage.task.bean.entity.TCustTask;
import com.samton.web.manage.task.bean.excel.CustTaskInfoEntity;
import com.samton.web.manage.task.bean.excel.CustTaskReportEntity;
import com.samton.web.manage.task.bean.vo.*;
import com.samton.web.manage.task.dao.TCustTaskMapper;
import com.samton.web.manage.task.service.ICustTaskService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: 客户消费记录实现类
 * @Author: ZhongShengbin
 * @Date: 2020/04/15 16:31
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Service
public class CustTaskServiceImpl extends SuperServiceIntegerImpl<TCustTaskMapper, TCustTask> implements ICustTaskService {

    @Resource
    private TCustTaskMapper tCustTaskMapper;

    public static final Logger log = LoggerFactory.getLogger(CustTaskServiceImpl.class);

    @Resource
    private TCustOrderMapper orderMapper;
    @Resource
    private TBuyersMapper buyersMapper;
    @Resource
    private TSysPmUserMapper pmUserMapper;
    @Resource
    private TCustAccountMapper custAccountMapper;
    @Resource
    private TCustProductMapper custProductMapper;

    @Override
    public Pagination<CustTaskVo> queryTaskPageList(Pagination<CustTaskVo> paramBean) throws Exception {
        Pagination<CustTaskVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustTaskVo> list = mapper.queryPageTaskList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public Pagination<CustTaskEntity> exportTaskPageList(Pagination<CustTaskVo> paramBean) throws Exception {
        Pagination<CustTaskEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustTaskEntity> list = mapper.exportTaskInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public CustTaskVo selectTaskVoById(Integer itemId, Integer orderId) throws Exception {
        return this.mapper.selectTaskVoById(itemId, orderId);
    }

    /**
     * 任务信息分页查询
     *
     * @param paramBean
     * @return
     */
    @Override
    public Pagination<CustTaskInfoVo> queryPageCustTaskInfoList(Pagination<CustTaskInfoVo> paramBean) throws Exception {
        Pagination<CustTaskInfoVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustTaskInfoVo> list = mapper.queryPageCustTaskInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 任务信息导出
     *
     * @param paramBean
     */
    @Override
    public Pagination<CustTaskInfoEntity> exportPageCustTaskInfoList(Pagination<CustTaskInfoVo> paramBean) throws Exception {
        Pagination<CustTaskInfoEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustTaskInfoEntity> list = mapper.exportPageCustTaskInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 任务报告信息导出
     *
     * @param paramBean
     */
    @Override
    public Pagination<CustTaskReportEntity> exportPageCustTaskReportList(Pagination<CustTaskInfoVo> paramBean) throws Exception {
        Pagination<CustTaskReportEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustTaskReportEntity> list = mapper.exportPageCustTaskReportList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 任务信息查看
     *
     * @param taskId
     */
    @Override
    public CustTaskInfoVo findCustTaskInfoById(Integer taskId) throws Exception {
        return this.mapper.selectCustTaskInfoById(taskId);
    }

    /**
     * 批量转单
     *
     * @param taskIds
     * @param modifyUserId
     * @param modifyDate
     * @param liableUserId
     * @return
     */
    @Override
    public int updateLiableUserBatchById(Integer[] taskIds, Long modifyUserId, Date modifyDate, Integer liableUserId) throws Exception {
        return this.mapper.updateLiableUserBatchById(taskIds, modifyUserId, modifyDate, liableUserId);
    }


    /**
     * 导入任务
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, String> importTasks(CommonsMultipartFile file) throws Exception {
        Map<String, String> result = new HashMap<>();

        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<CustTaskExcelVo> custTaskExcelVos = ExcelImportUtil.importExcel(file.getInputStream(), CustTaskExcelVo.class, params);
        if (custTaskExcelVos == null || custTaskExcelVos.isEmpty()) {
            log.info("importTasks error, tasks is empty!");
            return null;
        }
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        boolean flag = true;
        //先判断
        //订单编号	买家账号代码	订单数量	税收($)	执行日期	应充金额($)	任务负责人	备注
        for (int i = 0; i < custTaskExcelVos.size(); i++) {
            CustTaskExcelVo custTaskExcelVo = custTaskExcelVos.get(i);
            //1.订单编号、买家账号代码、订单数量、执行日期、应充金额($)、任务负责人 不能为空
            if (StringUtils.isBlank(custTaskExcelVo.getOrderCode()) || StringUtils.isBlank(custTaskExcelVo.getBuyerCode()) ||
                    custTaskExcelVo.getOrderNum() == null || custTaskExcelVo.getExecDate() == null ||
                    custTaskExcelVo.getDepositMoney() == null || StringUtils.isBlank(custTaskExcelVo.getLiableUserName())) {
                result.put("error", "第" + (i + 2) + "行数据有问题：订单编号、买家账号代码、订单数量、执行日期、应充金额($)、任务负责人不能为空。");
                flag = false;
            }
            //2.订单编号是否存在
            TCustOrder custOrder = null;
            CustAccountVo custAccount = null;
            TCustProduct custProduct = null;
            if (flag) {
                custOrder = orderMapper.selectCustOrderByOrderCode(custTaskExcelVo.getOrderCode());
                if (custOrder == null) {
                    result.put("error", "第" + (i + 2) + "行数据有问题：订单编号【" + custTaskExcelVo.getOrderCode() + "】不存在或订单状态已进行时。");
                    flag = false;
                }
            }
            //3.买家账号是否存在
            TBuyers buyers = null;
            if (flag) {
                buyers = buyersMapper.selectBuyerByBuyerCode(custTaskExcelVo.getBuyerCode());
                if (buyers == null) {
                    result.put("error", "第" + (i + 2) + "行数据有问题：买家账号【" + custTaskExcelVo.getBuyerCode() + "】不存在。");
                    flag = false;
                }
            }
            //4.任务负责人是否存在
            TSysPmUser pmUser = null;
            if (flag) {
                pmUser = pmUserMapper.queryUserByUsername(custTaskExcelVo.getLiableUserName());
                if (pmUser == null) {
                    result.put("error", "第" + (i + 2) + "行数据有问题：任务负责人【" + custTaskExcelVo.getLiableUserName() + "】不存在。");
                    flag = false;
                }
            }

            if (!flag) {
                log.error("import task error, error task info: {}", JSON.toJSONString(custTaskExcelVo));
                return result;
            }
            custAccount = custAccountMapper.selectByCustId(custOrder.getCustId());
            custProduct = custProductMapper.selectByPrimaryKey(custOrder.getProductId());

            //正式插入
            try {
                TCustTask custTask = new TCustTask();
                custTask.setTaskCode(IdGen.getBusinessCode("TSK" + custOrder.getDemandType()));
                custTask.setOrderId(custOrder.getOrderId());
                custTask.setCustId(custOrder.getCustId());
                custTask.setProductId(custOrder.getProductId());
                custTask.setBuyerId(buyers.getBuyerId());
                custTask.setOrderNum(custTaskExcelVo.getOrderNum());
                custTask.setUnitPrice(custOrder.getUnitPrice());
                custTask.setTax(custTaskExcelVo.getTax());

                /*订单金额(元)，每次编辑时需要再计算
                刷单订单金额(元)=刷单数量（默认为1）*商品单价*美元汇率*刷单周期*刷单频次
                采购订单金额(元)=(订单单价*订单数量+税收)*美元汇率*/
                BigDecimal orderMoney = null;
                if (custOrder.getDemandType() == 1) { // 采购
                    orderMoney = custOrder.getUnitPrice().multiply(new BigDecimal(custTaskExcelVo.getOrderNum()))
                            .add(custTaskExcelVo.getTax()).multiply(custAccount.getDollarRate());
                } else { // 刷单
                    orderMoney = custOrder.getUnitPrice().multiply(new BigDecimal(custTaskExcelVo.getOrderNum()))
                            .multiply(custAccount.getDollarRate()).multiply(new BigDecimal(custOrder.getFrequency()))
                            .multiply(new BigDecimal(custOrder.getCycle()));
                }
                custTask.setOrderMoney(orderMoney);

                //订单号=订单日期&负责人&账号(店铺名称)&客户账号&产品ID&订单数量
                custTask.setOrderSn(DateUtil.formatDatetime(custTaskExcelVo.getExecDate(), "yyyyMMdd") +
                        custTaskExcelVo.getLiableUserName() + buyers.getBuyerCode() + custProduct.getAccountsName() +
                        custProduct.getItemId() + custOrder.getOrderNum());

                //custTask.setFreightSn();

                //地址，采购是订单表中的地址。刷单是t_buyers中拼接的地址（账户地址&城市&州&邮编）
                String address = null;
                if (custOrder.getDemandType() == 1) { // 采购
                    address = custOrder.getAddress();
                } else { // 刷单
                    address = buyers.getAddress() + buyers.getCity() + buyers.getProvince() + buyers.getPostcode();
                }
                custTask.setAddress(address);

//                custTask.setReturnMoney();
                custTask.setAccountMoney(custAccount.getAccountMoney());
                custTask.setDepositMoney(custTaskExcelVo.getDepositMoney());
//                custTask.setCardsCode();
                custTask.setExecDate(custTaskExcelVo.getExecDate());
                custTask.setTaskState(0);
                custTask.setLiableUserId(pmUser.getUserId());
//                custTask.setTaskFinishTime();
//                custTask.setTaskFinishNote();
                custTask.setRemark(custTaskExcelVo.getRemark());
                custTask.setCreateDate(new Date());
                custTask.setCreateUserId(currentUser.getUserId());
                custTask.setModifyDate(new Date());
                custTask.setModifyUserId(currentUser.getUserId());

                this.mapper.insertSelective(custTask);

            } catch (Exception e) {
                e.printStackTrace();
                log.error("import task error, error task info: {}", JSON.toJSONString(custTaskExcelVo));
                result.put("error", "第" + (i + 2) + "行数据有问题！");
                return result;
            }

        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 设置任务完成
     *
     * @param custTaskInfoVo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String reportSettingOk(CustTaskInfoVo custTaskInfoVo) throws Exception {
        TCustTask task = custTaskInfoVo == null ? null : custTaskInfoVo.getTask();
        if (task == null) return "参数错误：task信息错误";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("taskId", task.getTaskId());
        paramMap.put("taskState", task.getTaskState());
        paramMap.put("cardsCode", task.getCardsCode());
        paramMap.put("modifyUserId", CurrentUtil.getCurrentUser().getUserId());
        paramMap.put("orderNum", task.getOrderNum());
        paramMap.put("orderMoney", task.getOrderMoney());
        if (task.getTax() != null) {
            paramMap.put("tax", task.getTax());
        }
        paramMap.put("taskFinishNote", task.getTaskFinishNote());

        return this.mapper.updateTaskOk(JsonUtil.stringify(paramMap));
    }

    @Override
    public boolean editRefund(CustTaskInfoVo custTaskInfoVo) throws Exception {
        TCustTask task = custTaskInfoVo == null ? null : custTaskInfoVo.getTask();
        if (task == null) {
            return false;
        }
        int update = mapper.updateByPrimaryKeySelective(task);
        return update > 0;
    }

    /**
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @Author ZhongShengbin
     * @Description 批量设置任务完成状态
     * @Date 2020/4/28 0028
     * @Param [file]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> importReportSettingOk(CommonsMultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(0);
        Map<String, String> result;
        List<TaskSettingOkExcelVo> taskExcelVos = ExcelImportUtil.importExcel(file.getInputStream(), TaskSettingOkExcelVo.class, importParams);
        if (taskExcelVos == null || taskExcelVos.isEmpty()) {
            log.info("importReportSetting error, setting is empty!");
            return null;
        }
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        boolean flag = true;
        for (int i = 0; i < taskExcelVos.size(); i++) {
            TaskSettingOkExcelVo taskExcelVo = taskExcelVos.get(i);
            if (StringUtils.isBlank(taskExcelVo.getCardsCode()) || StringUtils.isBlank(taskExcelVo.getTaskCode())) {
                flag = false;
            }
            if (taskExcelVo.getTaskState() == null || (!"已完成".equals(taskExcelVo.getTaskState()) && !"已失败".equals(taskExcelVo.getTaskState()))) {
                flag = false;
            }
            if (!flag) {
                log.error("import orders error, error order info: {}", JSON.toJSONString(taskExcelVo));
                result = new HashMap<>();
                result.put("error", "第" + (i + 2) + "行数据有问题！");
                return result;
            }

        }
        int count = 0;//做计数器
        //开始执行
        for (TaskSettingOkExcelVo taskExcelVo : taskExcelVos) {

            TCustTask task = tCustTaskMapper.selectTaskByCode(taskExcelVo.getTaskCode());

            //如果输入的任务编号不存在
            if (task == null) {
                Map<String, String> results = new HashMap<>();
                log.error("import task error, error task info: {}", JSON.toJSONString(taskExcelVo));
                String errorStr = "第" + (count + 2) + "行任务编号不存在！";
                throw new NullPointerException(errorStr);
            }

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("taskId", task.getTaskId());
            paramMap.put("taskState", "已完成".equals(taskExcelVo.getTaskState()) ? 1 : 2);
            paramMap.put("cardsCode", taskExcelVo.getCardsCode());
            paramMap.put("modifyUserId", CurrentUtil.getCurrentUser().getUserId());
            paramMap.put("orderNum", taskExcelVo.getOrderNum());
            paramMap.put("orderMoney", taskExcelVo.getOrderMoney());
            if (taskExcelVo.getTax() != null) {
                paramMap.put("tax", taskExcelVo.getTax());
            }
            paramMap.put("taskFinishNote", taskExcelVo.getTaskFinishNote());

            String updateRes = this.mapper.updateTaskOk(JsonUtil.stringify(paramMap));
            if (!"".equals(updateRes)) {
                result = new HashMap<>();
                result.put("error", "第" + (count + 2) + "行数据更新失败！");
                return result;
            }

            count++;
        }
        return Collections.EMPTY_MAP;
    }


    /**
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @Author ZhongShengbin
     * @Description 批量设置运单号
     * @Date 2020/4/28 0028
     * @Param [file]
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> importReportSettingSn(CommonsMultipartFile file) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(0);
        importParams.setHeadRows(1);
        List<TaskSettingSnExcelVo> taskExcelVos = ExcelImportUtil.importExcel(file.getInputStream(), TaskSettingSnExcelVo.class, importParams);
        if (taskExcelVos == null || taskExcelVos.isEmpty()) {
            log.info("importReportSetting error, setting is empty!");
            return null;
        }
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        boolean flag = true;
        for (int i = 0; i < taskExcelVos.size(); i++) {
            TaskSettingSnExcelVo taskSettingSnExcelVo = taskExcelVos.get(i);
            if (StringUtils.isBlank(taskSettingSnExcelVo.getTaskCode()) || StringUtils.isBlank(taskSettingSnExcelVo.getFreightSn())) {
                flag = false;
            }
            if (!flag) {
                log.error("import orders error, error order info: {}", JSON.toJSONString(taskSettingSnExcelVo));
                Map<String, String> result = new HashMap<>();
                result.put("error", "第" + (i + 2) + "行数据有问题！");
                return result;
            }
        }
        int count = 0;
        for (TaskSettingSnExcelVo taskSettingSnExcelVo : taskExcelVos) {
            TCustTask task = tCustTaskMapper.selectTaskByCode(taskSettingSnExcelVo.getTaskCode());
            if (task == null) {
                Map<String, String> results = new HashMap<>();
                log.error("import task error, error task info: {}", JSON.toJSONString(taskSettingSnExcelVo));
                String errorStr = "第" + (count + 2) + "行任务编号不存在！";
                throw new NullPointerException(errorStr);
            }

            TCustTask taskNew = new TCustTask();
            taskNew.setTaskId(task.getTaskId());
            taskNew.setFreightSn(taskSettingSnExcelVo.getFreightSn());
            taskNew.setModifyDate(new Date());
            taskNew.setModifyUserId(CurrentUtil.getCurrentUser().getUserId());

            tCustTaskMapper.updateByPrimaryKeySelective(taskNew);

            count++;
        }
        return Collections.EMPTY_MAP;
    }

    /**
     * 自动分配任务
     *
     * @param type 分配类型：1-实时分配；2-定时分配
     * @return
     * @throws Exception
     */
    @Override
    public String autoAllocateTask(int type) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("type", 2);
        paramsMap.put("userId", 1);
        return this.mapper.autoAllocateTask(JsonUtil.stringify(paramsMap));
    }

    @Override
    public List<Map<String, Object>> queryBuyerCode(String buyerName) throws Exception {
        return this.mapper.queryBuyerCode(buyerName);
    }

}
