package com.samton.web.manage.buyer.constant;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import org.springframework.stereotype.Component;

//业务级别按模块自己划分，参考Module实体，1\2\3\4\5\6\7\8\9依次递增
//后三位错误编码，倒数第四位以上，模块ID
//每个模块报错编号预计1000个
@Component
public class BuyerExpCodeConstant extends ExpCodeConstant {

	public static final String ACCOUNT_EDIT_COLUMN_ERROR = "32001";
	public static final String ACCOUNT_EDIT_ID_ERROR = "32002";
	public static final String ACCOUNT_SET_ERROR = "32003";
	public static final String ACCOUNT_IMPORT_ERROR = "32004";


	static {
		msgMap.put(ACCOUNT_EDIT_COLUMN_ERROR, "编辑失败，请重新输入！");
		msgMap.put(ACCOUNT_EDIT_ID_ERROR, "参数错误，请重新输入！");
		msgMap.put(ACCOUNT_SET_ERROR, "启用/禁用失败，请联系管理员！");
		msgMap.put(ACCOUNT_IMPORT_ERROR, "买家账号批量导入失败，请联系管理员！");
	}

}
