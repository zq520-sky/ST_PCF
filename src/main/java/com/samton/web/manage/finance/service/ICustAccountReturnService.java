package com.samton.web.manage.finance.service;

import com.samton.platform.common.service.ISuperIntegerService;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustAccountReturn;
import com.samton.web.manage.finance.bean.excel.CustAccountCreditEntity;
import com.samton.web.manage.finance.bean.excel.CustAccountReturnEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountReturnVo;
import java.util.Map;

public interface ICustAccountReturnService extends ISuperIntegerService<TCustAccountReturn> {

    /**
     * 客户还款记录分页查询
     * @param paramBean
     * @return
     */
    public Pagination<CustAccountReturnVo> queryReturnPageList(Pagination<CustAccountReturnVo> paramBean) throws Exception;

    /**
     * 客户还款记录导出
     * @param paramBean
     */
    public Pagination<CustAccountReturnEntity> exportReturnPageList(Pagination<CustAccountReturnVo> paramBean) throws Exception;

    /**
     * 客户还款记录查看
     * @param returnId
     */
    public CustAccountReturnVo selectReturnVoById(Integer returnId) throws Exception;
}
