package com.samton.web.manage.order.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.common.util.DateUtil;
import com.samton.platform.common.util.IdGen;
import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.exception.ServiceException;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.JsonUtil;
import com.samton.web.manage.cust.bean.entity.TCustomer;
import com.samton.web.manage.cust.bean.vo.CustProductVo;
import com.samton.web.manage.cust.dao.TCustProductMapper;
import com.samton.web.manage.cust.dao.TCustomerMapper;
import com.samton.web.manage.finance.bean.vo.CustAccountVo;
import com.samton.web.manage.finance.dao.TCustAccountMapper;
import com.samton.web.manage.order.bean.entity.TCustOrder;
import com.samton.web.manage.order.bean.excel.CustOrderExcel;
import com.samton.web.manage.order.bean.vo.CustOrderDto;
import com.samton.web.manage.order.bean.vo.CustOrderVo;
import com.samton.web.manage.order.bean.vo.OrderExcelVo;
import com.samton.web.manage.order.constants.CustOrderExpCodeConstants;
import com.samton.web.manage.order.dao.TCustOrderMapper;
import com.samton.web.manage.order.enums.DemandTypeEnums;
import com.samton.web.manage.order.enums.OrderStatusEnums;
import com.samton.web.manage.order.service.ICustOrderService;
import com.samton.web.manage.task.dao.TCustTaskMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: CustOrderServiceImpl
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-16 18:10
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
@Service
public class CustOrderServiceImpl extends SuperServiceIntegerImpl<TCustOrderMapper, TCustOrder> implements ICustOrderService {

    public static final Logger log = LoggerFactory.getLogger(CustOrderServiceImpl.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Resource
    private TCustProductMapper tCustProductMapper;
    @Resource
    private TCustAccountMapper tCustAccountMapper;
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TCustTaskMapper custTaskMapper;

    @Override
    public Pagination<CustOrderVo> queryOrderPageList(Pagination<CustOrderVo> paramBean) throws Exception {
        Pagination<CustOrderVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustOrderVo> list = mapper.queryOrderPageList(paramBean, pagination.getRowBounds());
        list.stream().forEach(custOrderVo -> {
            custOrderVo.setOrderStateCN(OrderStatusEnums.getDescByState(custOrderVo.getOrderState()));
            custOrderVo.setDemandTypeCN(DemandTypeEnums.getDescByState(custOrderVo.getDemandType()));
        });
        pagination.setData(list);
        return pagination;
    }

    @Override
    public Pagination<CustOrderExcel> exportOrderPageList(Pagination<CustOrderVo> paramBean) throws Exception {
        Pagination<CustOrderExcel> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustOrderExcel> list = mapper.exportOrderPageList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public CustOrderVo selectCustOrderById(Integer orderId) throws Exception {
        return mapper.selectOrderById(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, String> importOrders(CommonsMultipartFile file, Integer type) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<OrderExcelVo> orderExcelVos = ExcelImportUtil.importExcel(file.getInputStream(), OrderExcelVo.class, params);
        if (orderExcelVos == null || orderExcelVos.isEmpty()) {
            log.info("importOrders error, orders is empty!");
            return null;
        }
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        boolean flag = true;
        //错误集
        Map<String, String> result = new HashMap<>();
        //先判断
        for(int i=0; i<orderExcelVos.size(); i++){
            OrderExcelVo orderExcelVo = orderExcelVos.get(i);
            Integer demandType = DemandTypeEnums.getStateByDesc(orderExcelVo.getDemandType());
            if (StringUtils.isBlank(orderExcelVo.getDemandType()) || StringUtils.isBlank(orderExcelVo.getPlatformType()) || StringUtils.isBlank(orderExcelVo.getAccountsName())
                    || StringUtils.isBlank(orderExcelVo.getItemId()) || StringUtils.isBlank(orderExcelVo.getTitleKeyword()) || orderExcelVo.getTaskStartDate() == null) {
                flag = false;
            }
            //采购，地址不能为空
            if(demandType == 1 && StringUtils.isBlank(orderExcelVo.getAddress())){
                flag = false;
            }
            //单价和订单数量不能为空
            if (orderExcelVo.getUnitPrice() == null || orderExcelVo.getOrderNum() == null) {
                flag = false;
            }
            //刷单频次、周期不能为空
            if (demandType == 2) {
                if ( orderExcelVo.getFrequency() == null || orderExcelVo.getCycle() == null) {
                    flag = false;
                }
            }
            //判断任务执行日期>=当天
            SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today = sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00");
            if(orderExcelVo.getTaskStartDate() == null || DateUtil.isBefore(orderExcelVo.getTaskStartDate(), today)){
                result.put("error", "第"+(i+2)+"行：任务开始时间错误！");
                return result;
            }

            if (!flag) {
                log.error("import orders error, error order info: {}", JSON.toJSONString(orderExcelVo));
                result.put("error", "第"+(i+2)+"行数据有问题！");
                return result;
            }
        }

        //判断客户余额是否足够
        result = judgeMoneyEnough(orderExcelVos, type);
        if(StringUtils.isNotBlank(result.get("error"))){
            return result;
        }

        for (OrderExcelVo orderExcelVo : orderExcelVos) {
            CustOrderDto custOrderDto = new CustOrderDto();
            if (type == 1) {
                TCustomer tCustomer = tCustomerMapper.selectByCustName(orderExcelVo.getCustName().trim());
                if(tCustomer == null){
                    result.put("error", "客户名称【"+orderExcelVo.getCustName()+"】不存在！");
                    return result;
                }
                custOrderDto.setCustId(tCustomer.getCustId());
            } else {
                custOrderDto.setCustId(currentUser.getEnterpriseId());
            }
            custOrderDto.setAccountsName(orderExcelVo.getAccountsName());
            custOrderDto.setAddress(orderExcelVo.getAddress());
            custOrderDto.setDemandType(DemandTypeEnums.getStateByDesc(orderExcelVo.getDemandType()));
            custOrderDto.setPlatformType(orderExcelVo.getPlatformType());
            custOrderDto.setTitleKeyword(orderExcelVo.getTitleKeyword());
            custOrderDto.setItemId(orderExcelVo.getItemId());
            custOrderDto.setOrderNum(orderExcelVo.getOrderNum());
            custOrderDto.setUnitPrice(orderExcelVo.getUnitPrice());
            custOrderDto.setFrequency(orderExcelVo.getFrequency());
            custOrderDto.setCycle(orderExcelVo.getCycle());
            custOrderDto.setRemark(orderExcelVo.getRemark());
            custOrderDto.setTaskStartDate(orderExcelVo.getTaskStartDate());

            insertOrder(custOrderDto);
        }
        return Collections.EMPTY_MAP;
    }

    private Map<String, String> judgeMoneyEnough(List<OrderExcelVo> orderExcelVos, Integer type) throws Exception {
        Map<Integer, BigDecimal> totalMoneyMap = new HashMap<>();
        Map<Integer, CustAccountVo> custAccountVoMap = new HashMap<>();
        Map<String, String> result = new HashMap<>();
        for (OrderExcelVo orderExcelVo : orderExcelVos) {
            Integer custId;
            if (type == 1) {
                TCustomer tCustomer = tCustomerMapper.selectByCustName(orderExcelVo.getCustName().trim());
                if(tCustomer == null){
                    result.put("error", "客户名称【"+orderExcelVo.getCustName()+"】不存在！");
                    return result;
                }
                custId = tCustomer.getCustId();
            } else {
                custId = CurrentUtil.getCurrentUser().getEnterpriseId();
            }
            CustAccountVo custAccountVo = custAccountVoMap.get(custId);
            if(custAccountVo == null){
                custAccountVo = tCustAccountMapper.selectByCustId(custId);
                custAccountVoMap.put(custId, custAccountVo);
            }
            Integer demandType = DemandTypeEnums.getStateByDesc(orderExcelVo.getDemandType());
            //采购
            BigDecimal unitPrice = orderExcelVo.getUnitPrice();
            Integer orderNum = orderExcelVo.getOrderNum();
            Integer frequency = orderExcelVo.getFrequency();
            Integer cycle = orderExcelVo.getCycle();
            BigDecimal dollarRate = custAccountVo.getDollarRate();

            BigDecimal totalMoney = totalMoneyMap.get(custId);
            BigDecimal orderMoney = new BigDecimal("0");
            if(totalMoney == null){
                totalMoney = new BigDecimal("0");
            }
            //采购
            if (demandType == 1) {
                orderMoney = unitPrice.multiply(new BigDecimal(orderNum)).multiply(new BigDecimal(1.1)).multiply(dollarRate);

            }
            //刷单
            if (demandType == 2) {
                orderMoney = unitPrice.multiply(new BigDecimal(orderNum * frequency * cycle)).multiply(dollarRate);
            }
            totalMoneyMap.put(custId, totalMoney.add(orderMoney));
        }

        //判断客户金额是否足够
        Set<Map.Entry<Integer, BigDecimal>> entries = totalMoneyMap.entrySet();
        StringBuilder custNames = new StringBuilder();
        for (Map.Entry<Integer, BigDecimal> entry : entries) {
            Integer custId = entry.getKey();
            BigDecimal totalMoney = entry.getValue();
            CustAccountVo custAccountVo = custAccountVoMap.get(custId);
            if(custAccountVo.getAccountMoney().add(custAccountVo.getCreditMoney()).compareTo(totalMoney) < 0){
                custNames.append(custAccountVo.getCustName());
                custNames.append(",");
            }
        }
        String name = custNames.toString();

        if(StringUtils.isNotBlank(name)){
            name = name.substring(0,name.length()-1);
            result.put("error", name + "账户余额不足");
            return result;
        }

        return Collections.EMPTY_MAP;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String addCustOrder(CustOrderDto custOrderDto) throws Exception {
        return insertOrder(custOrderDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateCustOrder(CustOrderDto custOrderDto) throws Exception {

        //判断任务执行日期>=当天
        SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00");
        if(custOrderDto.getTaskStartDate() == null || DateUtil.isBefore(custOrderDto.getTaskStartDate(), today)){
            return "-2~任务执行日期错误！";
        }

        CustProductVo custProductVo = tCustProductMapper.selectProductByItemId(custOrderDto.getItemId());
        TCustOrder custOrder = mapper.selectByPrimaryKey(custOrderDto.getOrderId());
        BigDecimal unitPrice = custOrderDto.getUnitPrice();
        Integer orderNum = custOrderDto.getOrderNum();
        Integer frequency = custOrderDto.getFrequency();
        Integer cycle = custOrderDto.getCycle();
        custOrder.setCustId(custOrderDto.getCustId());
        custOrder.setDemandType(custOrderDto.getDemandType());
        custOrder.setProductId(custProductVo.getProductId());
        custOrder.setAddress(custOrderDto.getAddress());
        custOrder.setUnitPrice(unitPrice);
        custOrder.setOrderNum(custOrderDto.getOrderNum());
        custOrder.setRemark(custOrderDto.getRemark());
        custOrder.setTaskStartDate(custOrderDto.getTaskStartDate());
        custOrder.setFrequency(frequency);
        custOrder.setCycle(cycle);
        //获取汇率
        CustAccountVo custAccountVo = tCustAccountMapper.selectByCustId(custOrderDto.getCustId());
        BigDecimal dollarRate = custAccountVo.getDollarRate();
        custOrder.setDollarRate(dollarRate);
        BigDecimal totalMoney = null;
        //采购
        if (custOrderDto.getDemandType() == 1) {
            custOrder.setTax(unitPrice.multiply(new BigDecimal(orderNum)).multiply(new BigDecimal(0.1)));
            totalMoney = unitPrice.multiply(new BigDecimal(orderNum)).multiply(new BigDecimal(1.1)).multiply(dollarRate);
        }
        //刷单
        if (custOrderDto.getDemandType() == 2) {
            custOrder.setTax(new BigDecimal(0));
            custOrder.setTaskEndDate(DateUtil.addDays(custOrderDto.getTaskStartDate(), custOrderDto.getCycle()));
            totalMoney = unitPrice.multiply(new BigDecimal(orderNum * frequency * cycle)).multiply(dollarRate);
        }
        custOrder.setOrderTotalMoney(totalMoney);
        //判断账户余额+授信余额是否足够
        if(custAccountVo.getAccountMoney().add(custAccountVo.getCreditMoney()).compareTo(totalMoney) < 0){
            return "-1~账户余额不足";
        }
        int update = mapper.updateByPrimaryKeySelective(custOrder);

        //如果执行任务是同一天，则执行任务分配
        if(update > 0 && DateUtil.isSameDate(new Date(), custOrder.getTaskStartDate())){
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("orderId", custOrder.getOrderId());
            paramsMap.put("type", 1);
            paramsMap.put("userId", CurrentUtil.getCurrentUser().getUserId());

            String result = custTaskMapper.autoAllocateTask(JsonUtil.stringify(paramsMap));
            log.info(result);
        }

        return "";
    }

    @Override
    public boolean cancel(Integer orderId) throws Exception {
        TCustOrder custOrder = mapper.selectByPrimaryKey(orderId);
        if (custOrder.getOrderState() != 0) {
            throw new ServiceException(CustOrderExpCodeConstants.CUST_ORDER_CANCEL_ERROR);
        }
        custOrder.setOrderState(3);
        int update = mapper.updateByPrimaryKeySelective(custOrder);
        return update > 0;
    }

    @Override
    public boolean del(Integer orderId) throws Exception {
        TCustOrder custOrder = mapper.selectByPrimaryKey(orderId);
        if (custOrder.getOrderState() != 0) {
            throw new ServiceException(CustOrderExpCodeConstants.CUST_ORDER_DEL_ERROR);
        }
        int del = mapper.deleteByPrimaryKey(orderId);
        return del > 0;
    }

    private TCustOrder newCustOrder() {
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        Date currentDate = new Date();
        TCustOrder custOrder = new TCustOrder();
        custOrder.setOrderState(0);
        custOrder.setReleaseTime(currentDate);
        custOrder.setReleaseUserId(Integer.parseInt(String.valueOf(currentUser.getUserId())));
        custOrder.setCreateDate(currentDate);
        custOrder.setModifyDate(currentDate);
        custOrder.setCreateUserId(currentUser.getUserId());
        custOrder.setModifyUserId(currentUser.getUserId());
        return custOrder;
    }


    /**
     * 添加需求订单：
     * 先判断产品是否存在， 不存在则新增
     * 然后添加需求订单
     *
     * @param custOrderDto:
     * @author: wuzhiqiang
     * @date: 2020/4/24
     * @return: java.lang.String
     */
    private String insertOrder(CustOrderDto custOrderDto) throws Exception {
        Date currentDate = new Date();
        UserCacheBean currentUser = CurrentUtil.getCurrentUser();
        String orderCode = IdGen.getBusinessCode("ORD" + custOrderDto.getDemandType());

        //判断任务执行日期>=当天
        SimpleDateFormat sdfBegin = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = sdfParse.parse(sdfBegin.format(System.currentTimeMillis()) + " 00:00:00");
        if(custOrderDto.getTaskStartDate() == null || DateUtil.isBefore(custOrderDto.getTaskStartDate(), today)){
            return "-2~任务执行日期错误！";
        }

        CustProductVo custProductVo = tCustProductMapper.selectProductByItemId(custOrderDto.getItemId());
        //产品不存在则新增
        if (custProductVo == null) {
            //新增
            custProductVo = new CustProductVo();
            custProductVo.setCustId(custOrderDto.getCustId());
            custProductVo.setPlatformType(custOrderDto.getPlatformType());
            custProductVo.setAccountsName(custOrderDto.getAccountsName());
            custProductVo.setItemId(custOrderDto.getItemId());
            custProductVo.setTitleKeyword(custOrderDto.getTitleKeyword());
            custProductVo.setIsDel(0);
            custProductVo.setCreateDate(currentDate);
            custProductVo.setModifyDate(currentDate);
            custProductVo.setCreateUserId(currentUser.getUserId());
            custProductVo.setModifyUserId(currentUser.getUserId());
            tCustProductMapper.insertSelective(custProductVo);
        }

        //添加需求订单
        BigDecimal unitPrice = custOrderDto.getUnitPrice();
        Integer orderNum = custOrderDto.getOrderNum();
        Integer frequency = custOrderDto.getFrequency();
        Integer cycle = custOrderDto.getCycle();
        TCustOrder custOrder = newCustOrder();
        custOrder.setOrderCode(orderCode);
        custOrder.setCustId(custOrderDto.getCustId());
        custOrder.setDemandType(custOrderDto.getDemandType());
        custOrder.setProductId(custProductVo.getProductId());
        custOrder.setAddress(custOrderDto.getAddress());
        custOrder.setUnitPrice(custOrderDto.getUnitPrice());
        custOrder.setOrderNum(custOrderDto.getOrderNum());
        custOrder.setRemark(custOrderDto.getRemark());
        custOrder.setTaskStartDate(custOrderDto.getTaskStartDate());
        custOrder.setFrequency(frequency);
        custOrder.setCycle(cycle);

        //获取汇率
        CustAccountVo custAccountVo = tCustAccountMapper.selectByCustId(custOrderDto.getCustId());
        BigDecimal dollarRate = custAccountVo.getDollarRate();
        custOrder.setDollarRate(dollarRate);
        BigDecimal totalMoney = null;
        //采购
        if (custOrderDto.getDemandType() == 1) {
            custOrder.setTax(unitPrice.multiply(new BigDecimal(orderNum)).multiply(new BigDecimal(0.1)));
            totalMoney = unitPrice.multiply(new BigDecimal(orderNum)).multiply(new BigDecimal(1.1)).multiply(dollarRate);
        }
        //刷单
        if (custOrderDto.getDemandType() == 2) {
            custOrder.setTaskEndDate(DateUtil.addDays(custOrderDto.getTaskStartDate(), custOrderDto.getCycle()));
            totalMoney = unitPrice.multiply(new BigDecimal(orderNum * frequency * cycle)).multiply(dollarRate);
        }
        custOrder.setOrderTotalMoney(totalMoney);
        //判断账户余额+授信余额是否足够
        if(custAccountVo.getAccountMoney().add(custAccountVo.getCreditMoney()).compareTo(totalMoney) < 0){
            return "-1~账户余额不足";
        }
        int insert = mapper.insertSelective(custOrder);

        //如果执行任务是同一天，则执行任务分配
        if(insert > 0 && DateUtil.isSameDate(new Date(), custOrder.getTaskStartDate())){
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("orderId", custOrder.getOrderId());
            paramsMap.put("type", 1);
            paramsMap.put("userId", CurrentUtil.getCurrentUser().getUserId());

            String result = custTaskMapper.autoAllocateTask(JsonUtil.stringify(paramsMap));
            log.info(result);
        }

        return insert > 0 ? orderCode : "";
    }
}