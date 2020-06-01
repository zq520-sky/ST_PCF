package com.samton.web.manage.finance.constant;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import org.springframework.stereotype.Component;

//业务级别按模块自己划分，参考Module实体，1\2\3\4\5\6\7\8\9依次递增
//后三位错误编码，倒数第四位以上，模块ID
//每个模块报错编号预计1000个
@Component
public class CustAccountExpCodeConstant extends ExpCodeConstant {

	public static final String CUST_ACCOUNT_EDIT_COLUMN_ERROR = "13001";
	public static final String CUST_ACCOUNT_EDIT_PARAM_ERROR = "13002";
	public static final String CUST_ACCOUNT_ADD_FILE_EMPTY = "13003";
	public static final String CUST_ACCOUNT_ADD_ERROR = "13004";
	public static final String CUST_ACCOUNT_VERIFY_ERROR = "13005";


	static {
		msgMap.put(CUST_ACCOUNT_EDIT_COLUMN_ERROR, "编辑失败，请重新输入！");
		msgMap.put(CUST_ACCOUNT_EDIT_PARAM_ERROR, "参数错误，请重新输入！");
		msgMap.put(CUST_ACCOUNT_ADD_FILE_EMPTY, "请上传图片！");
		msgMap.put(CUST_ACCOUNT_ADD_ERROR, "充值申请失败！");
		msgMap.put(CUST_ACCOUNT_VERIFY_ERROR, "充值审核失败！");
	}

}
