package com.samton.web.manage.cust.service;

import com.samton.platform.common.service.ISuperIntegerService;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustLogLogin;

import java.util.Map;

/**
 * @Description: 客户登录日志Service
 * @Author: ZhongShengbin
 * @Date: 2020/04/17 09:24
 * Copyright (c) 2019, Samton. All rights reserved
 */
public interface ICustLogLoginService extends ISuperIntegerService<TCustLogLogin> {
    /**
     * 客户登录日志分页查询
     * @param paramBean
     * @return
     */
    public Pagination<TCustLogLogin> queryCustLogLoginPageList(Pagination<TCustLogLogin> paramBean) throws Exception;

    /**
     * 客户登录日志导出
     * @param paramBean
     */
    public Pagination<Map<String, Object>> exportCustLogLoginPageList(Pagination<TCustLogLogin> paramBean) throws Exception;

}
