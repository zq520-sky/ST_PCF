package com.samton.web.manage.buyer.service.impl;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.web.manage.buyer.bean.entity.TBuyers;
import com.samton.web.manage.buyer.bean.entity.TBuyersRecord;
import com.samton.web.manage.buyer.bean.excel.BuyersRecordExcel;
import com.samton.web.manage.buyer.bean.vo.BuyersExcelVo;
import com.samton.web.manage.buyer.bean.vo.BuyersVo;
import com.samton.web.manage.buyer.dao.TBuyersMapper;
import com.samton.web.manage.buyer.dao.TBuyersRecordMapper;
import com.samton.web.manage.buyer.service.IBuyerAccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 卖家账号管理
 * @Author: Huangqiuping
 * @Date: 2020/4/16 14:29
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Service("buyerAccountService")
public class BuyerAccountServiceImpl extends SuperServiceIntegerImpl<TBuyersMapper, TBuyers> implements IBuyerAccountService {

    private static final Logger log = LoggerFactory.getLogger(BuyerAccountServiceImpl.class);
    @Resource
    private TBuyersRecordMapper tBuyersRecordMapper ;
    /**
     * 通过recordId查询买家账号信息
     * @param recordId
     * @return
     * @throws Exception
     */
    public BuyersVo selectBuyerAccountByRecordId(Integer recordId) throws Exception{
        return this.mapper.selectBuyerAccountByRecordId(recordId);
    }

    /**
     * 买家账号分页查询
     * @param paramBean
     * @return
     */
    public Pagination<BuyersVo> queryPageBuyerVoList(Pagination<BuyersVo> paramBean) throws Exception{
        Pagination<BuyersVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<BuyersVo> list = this.mapper.queryPageBuyerVoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 买家账号管理导出
     * @param paramBean
     * @return
     */
    public Pagination<BuyersRecordExcel> exportPageBuyerVoList(Pagination<BuyersVo> paramBean) throws Exception{
        Pagination<BuyersRecordExcel> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<BuyersRecordExcel> list = this.mapper.exportPageBuyerVoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    /**
     * 更新卖家账号信息
     * @param tBuyers
     * @param tBuyersRecord
     * @return
     * @throws Exception
     */
    public boolean updateBuyerAccount(TBuyers tBuyers, TBuyersRecord tBuyersRecord) throws Exception{
        int result = this.mapper.updateByPrimaryKeySelective(tBuyers);
        this.tBuyersRecordMapper.updateByPrimaryKeySelective(tBuyersRecord);
        if(result > 0){
            return true ;
        }
        else{
            return false ;
        }
    }

    /**
     * csv格式 批量导入
     * @param datas
     * @throws Exception
     */
    public Map importAccount(List<String[]> datas) throws Exception {
        Map resultMap = new HashMap();
        try {
            if (datas!=null && datas.size()>0){
                StringBuffer platformTypeSb = new StringBuffer();
                StringBuffer buyerCodeSb = new StringBuffer();
                StringBuffer buyerNameSb = new StringBuffer();
                StringBuffer userNameSb = new StringBuffer();
                StringBuffer addressSb = new StringBuffer();
                StringBuffer citySb = new StringBuffer();
                StringBuffer provinceSb = new StringBuffer();
                StringBuffer postcodeSb = new StringBuffer();
                //StringBuffer registerTimeSb = new StringBuffer();

                StringBuffer accountMoneyHisSb = new StringBuffer();
                StringBuffer brushRechargeSb = new StringBuffer();
                StringBuffer purchaseRechargeSb = new StringBuffer();
                StringBuffer rechargeTotalSb = new StringBuffer();
                //StringBuffer quotaLimitSb = new StringBuffer();
                StringBuffer quotaMonthSb = new StringBuffer();
                StringBuffer brushNumSb = new StringBuffer();
                StringBuffer purchaseNumSb = new StringBuffer();
                StringBuffer brushTotalMoneySb = new StringBuffer();
                StringBuffer purchaseTotalMoneySb = new StringBuffer();
                StringBuffer refundMoneySb = new StringBuffer();
                StringBuffer accountMoneySb = new StringBuffer();

                datas.stream().forEach(buyersVo -> {
                    userNameSb.append(buyersVo[0]).append(",");
                    platformTypeSb.append(buyersVo[1]).append(",");
                    buyerCodeSb.append(buyersVo[2]).append(",");
                    buyerNameSb.append(buyersVo[3]).append(",");
                    addressSb.append(buyersVo[4]).append(",");

                    citySb.append(buyersVo[5]).append(",");
                    provinceSb.append(buyersVo[6]).append(",");
                    postcodeSb.append(buyersVo[7]).append(",");
                    accountMoneyHisSb.append(buyersVo[8]).append(",");
                    brushRechargeSb.append(buyersVo[9]).append(",");

                    purchaseRechargeSb.append(buyersVo[10]).append(",");
                    rechargeTotalSb.append(buyersVo[11]).append(",");
                    //quotaLimitSb.append(buyersVo.getQuotaLimit()).append(",");
                    quotaMonthSb.append(buyersVo[12]).append(",");
                    brushNumSb.append(buyersVo[13]).append(",");
                    purchaseNumSb.append(buyersVo[14]).append(",");

                    brushTotalMoneySb.append(buyersVo[15]).append(",");
                    purchaseTotalMoneySb.append(buyersVo[16]).append(",");
                    refundMoneySb.append(buyersVo[17]).append(",");
                    accountMoneySb.append(buyersVo[18]).append(",");
                    //registerTimeSb.append(buyersVo[19]).append(",");
                });
                HashMap parMap = new HashMap();
                parMap.put("userNameSb",userNameSb.toString());
                parMap.put("platformTypeSb",platformTypeSb.toString());
                parMap.put("buyerCodeSb",buyerCodeSb.toString());
                parMap.put("buyerNameSb",buyerNameSb.toString());
                parMap.put("addressSb",addressSb.toString());

                parMap.put("citySb",citySb.toString());
                parMap.put("provinceSb",provinceSb.toString());
                parMap.put("postcodeSb",postcodeSb.toString());
                parMap.put("accountMoneyHisSb",accountMoneyHisSb.toString());
                parMap.put("brushRechargeSb",brushRechargeSb.toString());

                parMap.put("purchaseRechargeSb",purchaseRechargeSb.toString());
                parMap.put("rechargeTotalSb",rechargeTotalSb.toString());
                //parMap.put("quotaLimitSb",quotaLimitSb.toString());
                parMap.put("quotaMonthSb",quotaMonthSb.toString());
                parMap.put("brushNumSb",brushNumSb.toString());
                parMap.put("purchaseNumSb",purchaseNumSb.toString());

                parMap.put("brushTotalMoneySb",brushTotalMoneySb.toString());
                parMap.put("purchaseTotalMoneySb",purchaseTotalMoneySb.toString());
                parMap.put("refundMoneySb",refundMoneySb.toString());
                parMap.put("accountMoneySb",accountMoneySb.toString());
                //parMap.put("registerTimeSb",registerTimeSb.toString());

                parMap.put("userId", CurrentUtil.getCurrentUser().getUserId());

                log.info("JSONStr:"+JSONObject.toJSONString(parMap));
                //插入数据表
                int result =  this.mapper.importBuyerAccount(JSONObject.toJSONString(parMap));
                if(result > 0){
                    resultMap.put("status",1);
                    resultMap.put("msg","买家账号导入成功,成功处理"+datas.size()+"条！");
                }
                else{
                    resultMap.put("status",-1);
                    resultMap.put("msg","买家账号导入信息为空！");
                }
            }
        } catch (Exception e) {
            log.error("买家账号导入出错，错误信息：", e.getMessage(), e);
            resultMap.put("status",-1);
            resultMap.put("msg","买家账号导入出错，请联系管理员！");
            //throw new ServiceException(BuyerExpCodeConstant.ACCOUNT_IMPORT_ERROR);
        }
        return resultMap ;
    }


    /**
     * Excel xls格式 批量导入
     * @param file
     * @throws Exception
     */
    public Map importAccount(CommonsMultipartFile file) throws Exception {
        Map resultMap = new HashMap();
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        try {
            List<BuyersExcelVo> buyersExcelVos = ExcelImportUtil.importExcel(file.getInputStream(), BuyersExcelVo.class, params);
            if(buyersExcelVos == null || buyersExcelVos.isEmpty()){
                log.info("买家账号导入信息，转换成对象为空!");
                resultMap.put("status",-1);
                resultMap.put("msg","买家账号导入信息，转换成对象为空！");
                return resultMap;
            }
            if (buyersExcelVos.size() > 5000) {
                resultMap.put("status",-1);
                resultMap.put("msg","导入数据条数不能超过5000条,请重新导入！");
                return resultMap;
            }

            StringBuffer platformTypeSb = new StringBuffer();
            StringBuffer buyerCodeSb = new StringBuffer();
            StringBuffer buyerNameSb = new StringBuffer();
            StringBuffer userNameSb = new StringBuffer();
            StringBuffer addressSb = new StringBuffer();
            StringBuffer citySb = new StringBuffer();
            StringBuffer provinceSb = new StringBuffer();
            StringBuffer postcodeSb = new StringBuffer();
            //StringBuffer registerTimeSb = new StringBuffer();

            StringBuffer accountMoneyHisSb = new StringBuffer();
            StringBuffer brushRechargeSb = new StringBuffer();
            StringBuffer purchaseRechargeSb = new StringBuffer();
            StringBuffer rechargeTotalSb = new StringBuffer();
            //StringBuffer quotaLimitSb = new StringBuffer();
            StringBuffer quotaMonthSb = new StringBuffer();
            StringBuffer brushNumSb = new StringBuffer();
            StringBuffer purchaseNumSb = new StringBuffer();
            StringBuffer brushTotalMoneySb = new StringBuffer();
            StringBuffer purchaseTotalMoneySb = new StringBuffer();
            StringBuffer refundMoneySb = new StringBuffer();
            StringBuffer accountMoneySb = new StringBuffer();
            StringBuffer payPalSb = new StringBuffer();
            StringBuffer emailSb = new StringBuffer();
            StringBuffer creditCardSb = new StringBuffer();

            buyersExcelVos.stream().forEach(buyersExcelVo -> {

                payPalSb.append(StringUtils.isBlank(buyersExcelVo.getPayPal()) ? "" : buyersExcelVo.getPayPal()).append(",");
                emailSb.append(StringUtils.isBlank(buyersExcelVo.getEmail()) ? "" : buyersExcelVo.getEmail()).append(",");
                creditCardSb.append(StringUtils.isBlank(buyersExcelVo.getCreditCard()) ? "" : buyersExcelVo.getCreditCard()).append(",");
                userNameSb.append(buyersExcelVo.getUserName()).append(",");
                platformTypeSb.append(buyersExcelVo.getPlatformType()).append(",");
                buyerCodeSb.append(buyersExcelVo.getBuyerCode()).append(",");
                buyerNameSb.append(buyersExcelVo.getBuyerName()).append(",");
                addressSb.append(buyersExcelVo.getAddress()).append(",");

                citySb.append(buyersExcelVo.getCity()).append(",");
                provinceSb.append(buyersExcelVo.getProvince()).append(",");
                postcodeSb.append(buyersExcelVo.getPostcode()).append(",");
                accountMoneyHisSb.append(buyersExcelVo.getAccountMoneyHis()).append(",");
                brushRechargeSb.append(0).append(",");

                purchaseRechargeSb.append(0).append(",");
                rechargeTotalSb.append(0).append(",");
                //quotaLimitSb.append(buyersExcelVo.getQuotaLimit()).append(",");
                quotaMonthSb.append(buyersExcelVo.getQuotaMonth()).append(",");
                brushNumSb.append(0).append(",");
                purchaseNumSb.append(0).append(",");

                brushTotalMoneySb.append(0).append(",");
                purchaseTotalMoneySb.append(0).append(",");
                refundMoneySb.append(0).append(",");
                accountMoneySb.append(buyersExcelVo.getAccountMoneyHis()).append(",");
                //registerTimeSb.append(buyersVo[19]).append(",");
            });
            HashMap parMap = new HashMap();
            parMap.put("payPalSb", payPalSb.toString());
            parMap.put("emailSb", emailSb.toString());
            parMap.put("creditCardSb", creditCardSb.toString());
            parMap.put("userNameSb",userNameSb.toString());
            parMap.put("platformTypeSb",platformTypeSb.toString());
            parMap.put("buyerCodeSb",buyerCodeSb.toString());
            parMap.put("buyerNameSb",buyerNameSb.toString());
            parMap.put("addressSb",addressSb.toString());

            parMap.put("citySb",citySb.toString());
            parMap.put("provinceSb",provinceSb.toString());
            parMap.put("postcodeSb",postcodeSb.toString());
            parMap.put("accountMoneyHisSb",accountMoneyHisSb.toString());
            parMap.put("brushRechargeSb",brushRechargeSb.toString());

            parMap.put("purchaseRechargeSb",purchaseRechargeSb.toString());
            parMap.put("rechargeTotalSb",rechargeTotalSb.toString());
            //parMap.put("quotaLimitSb",quotaLimitSb.toString());
            parMap.put("quotaMonthSb",quotaMonthSb.toString());
            parMap.put("brushNumSb",brushNumSb.toString());
            parMap.put("purchaseNumSb",purchaseNumSb.toString());

            parMap.put("brushTotalMoneySb",brushTotalMoneySb.toString());
            parMap.put("purchaseTotalMoneySb",purchaseTotalMoneySb.toString());
            parMap.put("refundMoneySb",refundMoneySb.toString());
            parMap.put("accountMoneySb",accountMoneySb.toString());
            //parMap.put("registerTimeSb",registerTimeSb.toString());

            parMap.put("userId", CurrentUtil.getCurrentUser().getUserId());

            log.info("买家账号导入JSONStr入库参数:"+JSONObject.toJSONString(parMap));
            //插入数据表
            int result =  this.mapper.importBuyerAccount(JSONObject.toJSONString(parMap));
            if(result > 0){
                resultMap.put("status",1);
                resultMap.put("msg","买家账号导入成功,成功处理"+buyersExcelVos.size()+"条！");
            }
            else{
                resultMap.put("status",-1);
                resultMap.put("msg","买家账号导入信息为空！");
            }
        } catch (Exception e) {
            log.error("买家账号导入出错，错误信息：", e.getMessage(), e);
            resultMap.put("status",-1);
            resultMap.put("msg","买家账号导入出错，请联系管理员！");
            //throw new ServiceException(BuyerExpCodeConstant.ACCOUNT_IMPORT_ERROR);
        }
        return resultMap ;
    }

    /**
     * 通过买家账号查询买家信息
     * @param buyerCode
     * @return
     * @throws Exception
     */
    @Override
    public TBuyers findBuyerByBuyerCode(String buyerCode) throws Exception {
        return this.mapper.selectBuyerByBuyerCode(buyerCode);
    }

}
