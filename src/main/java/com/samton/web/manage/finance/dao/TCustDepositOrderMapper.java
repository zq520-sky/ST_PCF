package com.samton.web.manage.finance.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustDepositOrder;
import com.samton.web.manage.finance.bean.excel.CustDepositOrderEntity;
import com.samton.web.manage.finance.bean.excel.CustRechargeApplicationEntity;
import com.samton.web.manage.finance.bean.vo.CustDepositOrderVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface TCustDepositOrderMapper extends AutoMapperInteger<TCustDepositOrder> {

    /** 客户充值记录查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustDepositOrderVo> queryPageChargeList(Pagination<CustDepositOrderVo> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 客户充值记录导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustDepositOrderEntity> exportOrderInfoList(Pagination<TCustDepositOrder> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 查询客户充值信息
     * @param orderId
     * @return
     * @throws Exception
     */
    public CustDepositOrderVo selectFinanceVoById(Integer orderId) throws Exception;

    /**
     * 客户充值记录导出
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustRechargeApplicationEntity> exportRechargeInfoList(Pagination<TCustDepositOrder> paramBean, RowBounds rowBounds) throws Exception;
}