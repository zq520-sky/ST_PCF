package com.samton.web.manage.cust.dao;


import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.web.manage.cust.bean.entity.TCustLogLogin;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface TCustLogLoginMapper extends AutoMapperInteger<TCustLogLogin> {

    /**
     * 客户登录日志查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<TCustLogLogin> queryPageCustLogLoginList(Pagination<TCustLogLogin> paramBean, RowBounds rowBounds)throws Exception;

    /**
     * 客户登录日志查询
     * @param paramBean
     * @param rowBounds
     * @return
     */
    List<Map<String,Object>> exportCustLogLoginInfoList(Pagination<TCustLogLogin> paramBean, RowBounds rowBounds)throws Exception;

}