package com.samton.web.manage.cust.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustLogLogin;
import com.samton.web.manage.cust.dao.TCustLogLoginMapper;
import com.samton.web.manage.cust.service.ICustLogLoginService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户登录日志实现类
 * @Author: ZhongShengbin
 * @Date: 2020/04/17 09:28
 * Copyright (c) 2019, Samton. All rights reserved
 */
@Service
public class TCustLogLoginServiceImpl extends SuperServiceIntegerImpl<TCustLogLoginMapper, TCustLogLogin> implements ICustLogLoginService {

    @Override
    public Pagination<TCustLogLogin> queryCustLogLoginPageList(Pagination<TCustLogLogin> paramBean) throws Exception {
        Pagination<TCustLogLogin> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<TCustLogLogin> list = mapper.queryPageCustLogLoginList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }

    @Override
    public Pagination<Map<String, Object>> exportCustLogLoginPageList(Pagination<TCustLogLogin> paramBean) throws Exception {
        Pagination<Map<String, Object>> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
        List<Map<String, Object>> list = mapper.exportCustLogLoginInfoList(paramBean, pagination.getRowBounds());
        pagination.setData(list);
        return pagination;
    }
}
