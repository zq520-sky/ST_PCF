package com.samton.web.manage.task.service.impl;

import com.samton.platform.common.service.impl.SuperServiceIntegerImpl;
import com.samton.web.manage.task.bean.entity.TCustTaskSet;
import com.samton.web.manage.task.dao.TCustTaskSetMapper;
import com.samton.web.manage.task.service.ICustTaskSetService;
import org.springframework.stereotype.Service;

/**
 * @Description: 任务设置Service接口实现类
 * @Author: YangYangen
 * @Date: 2020/4/17 13:44
 * Copyright (c) 2020, Samton. All rights reserved
*/
@Service("custTaskSetService")
public class CustTaskSetServiceImpl extends SuperServiceIntegerImpl<TCustTaskSetMapper, TCustTaskSet> implements ICustTaskSetService {


}
