package com.samton.web.manage.buyer.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.buyer.bean.entity.TBuyers;
import com.samton.web.manage.buyer.bean.excel.BuyersRecordExcel;
import com.samton.web.manage.buyer.bean.vo.BuyersVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface TBuyersMapper extends AutoMapperInteger<TBuyers> {

    /**
     * 买家账号通过记录id进行查询
     * @param recordId
     * @return
     */
    BuyersVo selectBuyerAccountByRecordId(Integer recordId);

    /**
     * 买家账号分页查询
     * @param paramBean
     * @return
     */
    List<BuyersVo> queryPageBuyerVoList(Pagination<BuyersVo> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 买家账号管理导出
     * @param paramBean
     * @return
     */
    List<BuyersRecordExcel> exportPageBuyerVoList(Pagination<BuyersVo> paramBean, RowBounds rowBounds) throws Exception;

    /**
     * 买家账号批量导入
     * @param josnStr
     * @return
     */
    int importBuyerAccount (String josnStr) throws Exception;

    /**
     * 通过买家账号查询买家信息
     * @param buyerCode
     * @return
     * @throws Exception
     */
    TBuyers selectBuyerByBuyerCode(String buyerCode) throws Exception;
}