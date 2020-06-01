package com.samton.web.manage.finance.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustAccountReturn;
import com.samton.web.manage.finance.bean.excel.CustAccountCreditEntity;
import com.samton.web.manage.finance.bean.excel.CustAccountReturnEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountReturnVo;
import com.samton.web.manage.finance.dao.TCustAccountReturnMapper;
import com.samton.web.manage.finance.service.ICustAccountReturnService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 还款记录Impl
 * @Author: ZhongShengbin
 * @Date: 2020/04/14 19:05
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Service
public class CustAccountReturnServiceImpl extends SuperServiceIntegerImpl<TCustAccountReturnMapper, TCustAccountReturn> implements ICustAccountReturnService {
    @Override
    public Pagination<CustAccountReturnVo> queryReturnPageList(Pagination<CustAccountReturnVo> paramBean) throws Exception {
        Pagination<CustAccountReturnVo> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustAccountReturnVo> list = mapper.queryPageReturnList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public Pagination<CustAccountReturnEntity> exportReturnPageList(Pagination<CustAccountReturnVo> paramBean) throws Exception {
        Pagination<CustAccountReturnEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustAccountReturnEntity> list = mapper.exportReturnInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public CustAccountReturnVo selectReturnVoById(Integer returnId) throws Exception {
        return this.mapper.selectReturnVoById(returnId);
    }
}
