package com.samton.web.manage.cust.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;

import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustProduct;
import com.samton.web.manage.cust.bean.excel.CustProductEntity;
import com.samton.web.manage.cust.bean.excel.CustomerEntity;
import com.samton.web.manage.cust.bean.vo.CustProductVo;
import com.samton.web.manage.cust.dao.TCustProductMapper;
import com.samton.web.manage.cust.service.ICustProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 卖家产品管理 Service接口实现类
 * @Author: Huangqiuping
 * @Date: 2020/4/13 14:06
 * Copyright (c) 2019, Samton. All rights reserved
*/
@Service("custProductService")
public class CustProductServiceImpl extends SuperServiceIntegerImpl<TCustProductMapper, TCustProduct> implements ICustProductService {

    /**
     * 通过productId查询卖家产品信息
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public CustProductVo selectCustProductVoById(Integer productId) throws Exception{
        return this.mapper.selectCustProductVoById(productId);
    }

    @Override
    public Map<String, Object> selectCustProductByItemId(String itemId) throws Exception {
        return mapper.selectCustProductByItemId(itemId);
    }

    @Override
    public Pagination<CustProductEntity> exportPageProductVoList(Pagination<TCustProduct> paramBean) throws Exception {
        Pagination<CustProductEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustProductEntity> list = mapper.exportPageProductVoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }


}
