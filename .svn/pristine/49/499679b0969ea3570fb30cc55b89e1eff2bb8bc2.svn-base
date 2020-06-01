package com.samton.web.manage.cust.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustProduct;
import com.samton.web.manage.cust.bean.entity.TCustomer;
import com.samton.web.manage.cust.bean.excel.CustProductEntity;
import com.samton.web.manage.cust.bean.excel.CustomerEntity;
import com.samton.web.manage.cust.bean.vo.CustProductVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;


public interface TCustProductMapper extends AutoMapperInteger<TCustProduct> {

    /**
     * 查询卖家产品信息
     * @param productId
     * @return
     * @throws Exception
     */
    public CustProductVo selectCustProductVoById(Integer productId) throws Exception;

    Map<String, Object> selectCustProductByItemId(String itemId) throws Exception;

    CustProductVo selectProductByItemId(String itemId) throws Exception;

    /**
     * 客户信息导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustProductEntity> exportPageProductVoList(Pagination<TCustProduct> paramBean, RowBounds rowBounds) throws Exception;
}