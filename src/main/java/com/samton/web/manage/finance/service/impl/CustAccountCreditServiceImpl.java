package com.samton.web.manage.finance.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustAccountCredit;
import com.samton.web.manage.finance.bean.excel.CustAccountCreditEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountCreditVo;
import com.samton.web.manage.finance.dao.TCustAccountCreditMapper;
import com.samton.web.manage.finance.service.ICustAccountCreditService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户授信记录ServiceImpl
 * @Author: ZhongShengbin
 * @Date: 2020/04/13 17:22
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Service
public class CustAccountCreditServiceImpl extends SuperServiceIntegerImpl<TCustAccountCreditMapper, TCustAccountCredit> implements ICustAccountCreditService {


    @Override
    public Pagination<TCustAccountCredit> queryCreditPageList(Pagination<TCustAccountCredit> paramBean) throws Exception {
        Pagination<TCustAccountCredit> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<TCustAccountCredit> list = mapper.queryPageCreditList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public Pagination<CustAccountCreditEntity> exportCreditPageList(Pagination<TCustAccountCredit> paramBean) throws Exception {
        Pagination<CustAccountCreditEntity> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<CustAccountCreditEntity> list = mapper.exportCreditInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public CustAccountCreditVo selectCreditVoById(Integer creditId) throws Exception {
        return this.mapper.selectCreditVoById(creditId);
    }
}
