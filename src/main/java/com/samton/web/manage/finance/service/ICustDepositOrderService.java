package com.samton.web.manage.finance.service;

import com.samton.platform.common.service.ISuperIntegerService;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustDepositOrder;
import com.samton.web.manage.finance.bean.excel.CustDepositOrderEntity;
import com.samton.web.manage.finance.bean.excel.CustRechargeApplicationEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountVo;
import com.samton.web.manage.finance.bean.vo.CustDepositOrderVo;

import java.util.Map;

public interface ICustDepositOrderService extends ISuperIntegerService<TCustDepositOrder> {

    /**
     * 客户充值记录分页查询
     * @param paramBean
     * @return
     */
    Pagination<CustDepositOrderVo> queryChargePageList(Pagination<CustDepositOrderVo> paramBean) throws Exception;

    /**
     * 客户充值记录导出
     * @param paramBean
     */
    Pagination<CustDepositOrderEntity> exportOrderPageList(Pagination<TCustDepositOrder> paramBean) throws Exception;


    /**
     * 客户充值记录查看
     * @param orderId
     */
    CustDepositOrderVo selectFinanceVoById(Integer orderId) throws Exception;

    /**
     * 新增充值申请
     * @author: wuzhiqiang
     * @date: 2020/4/20
     * @param custDepositOrderVo:
     * @return: boolean
     */
    String addCustRegisterOrder(CustDepositOrderVo custDepositOrderVo) throws Exception;

    /**
     * 充值审核
     * @author: wuzhiqiang
     * @date: 2020/4/21
     * @param custDepositOrderVo:
     * @return: boolean
     */
    boolean updateRegisterOrder(CustDepositOrderVo custDepositOrderVo) throws Exception;


    /**
     * 获取我的账户信息
     * @return
     */
    CustAccountVo findCustAccountByCustId() throws Exception;

    /**
     * 客户充值申请记录导出
     * @param paramBean
     */
    Pagination<CustRechargeApplicationEntity> exportRechargePageList(Pagination<TCustDepositOrder> paramBean) throws Exception;
}
