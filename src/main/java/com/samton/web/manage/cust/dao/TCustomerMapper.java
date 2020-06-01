package com.samton.web.manage.cust.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustomer;
import com.samton.web.manage.cust.bean.excel.CustomerEntity;
import com.samton.web.manage.cust.bean.vo.CustomerVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户信息Mapper接口
 * @Author: YangYangen
 * @Date: 2020/4/13 10:03
 * Copyright (c) 2020, Samton. All rights reserved
*/
public interface TCustomerMapper extends AutoMapperInteger<TCustomer> {
    /**
     * 客户信息分页查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustomerVo> queryPageCustomerVoList(Pagination<CustomerVo> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 客户信息导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustomerEntity> exportPageCustomerVoList(Pagination<TCustomer> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 新增客户信息
     * @param jsonStr
     * @return
     * @throws Exception
     */
    Integer insertCustomer(String jsonStr) throws Exception;

    /**
     * 查询客户信息
     * @param custId
     * @return
     * @throws Exception
     */
    CustomerVo selectCustomerVoById(Integer custId) throws Exception;

    Map<String, Object> selectCustAccountInfo(Integer custId) throws Exception;

    TCustomer selectByCustName(String custName) throws Exception;
}