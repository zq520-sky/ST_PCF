package com.samton.web.manage.cust.constant;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import org.springframework.stereotype.Component;

//业务级别按模块自己划分，参考Module实体，1\2\3\4\5\6\7\8\9依次递增
//后三位错误编码，倒数第四位以上，模块ID
//每个模块报错编号预计1000个
@Component
public class CustProductExpCodeConstant extends ExpCodeConstant {

	public static final String CUSTPRODUCT_EDIT_COLUMN_ERROR = "12001";
	public static final String CUSTPRODUCT_EDIT_ID_ERROR = "12002";


	static {
		msgMap.put(CUSTPRODUCT_EDIT_COLUMN_ERROR, "编辑失败，请重新输入！");
		msgMap.put(CUSTPRODUCT_EDIT_ID_ERROR, "参数错误，请重新输入！");
	}

}
