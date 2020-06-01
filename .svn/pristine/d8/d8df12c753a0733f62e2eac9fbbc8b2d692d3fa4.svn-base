package com.samton.web.manage.cust.service;

import com.samton.platform.common.service.ISuperIntegerService;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustProduct;
import com.samton.web.manage.cust.bean.excel.CustProductEntity;
import com.samton.web.manage.cust.bean.vo.CustProductVo;
import javassist.bytecode.ParameterAnnotationsAttribute;

import java.util.Map;


/**
 * @Description: 卖家产品管理
 * @Author: Huangqiuping
 * @Date: 2020/4/13 12:00
 * Copyright (c) 2019, Samton. All rights reserved
*/
public interface ICustProductService extends ISuperIntegerService<TCustProduct> {
    /**
     * 通过productId查询卖家产品信息
     * @param productId
     * @return
     * @throws Exception
     */
    public CustProductVo selectCustProductVoById(Integer productId) throws Exception;

    Map<String, Object> selectCustProductByItemId(String itemId) throws Exception;

    Pagination<CustProductEntity> exportPageProductVoList(Pagination<TCustProduct> paramBean)throws Exception;
}
