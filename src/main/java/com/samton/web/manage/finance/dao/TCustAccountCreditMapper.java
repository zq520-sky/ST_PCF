package com.samton.web.manage.finance.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.finance.bean.entity.TCustAccountCredit;
import com.samton.web.manage.finance.bean.excel.CustAccountCreditEntity;
import com.samton.web.manage.finance.bean.vo.CustAccountCreditVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TCustAccountCreditMapper extends AutoMapperInteger<TCustAccountCredit> {
    /**
     * 客户授信记录查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<TCustAccountCredit> queryPageCreditList(Pagination<TCustAccountCredit> paramBean, RowBounds rowBounds)throws Exception;

    /**
     * 客户授信记录查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<CustAccountCreditEntity> exportCreditInfoList(Pagination<TCustAccountCredit> paramBean, RowBounds rowBounds)throws Exception;

    /**
     * 查询客户授信信息
     * @param creditId
     * @return
     * @throws Exception
     */
    public CustAccountCreditVo selectCreditVoById(Integer creditId) throws Exception;

}