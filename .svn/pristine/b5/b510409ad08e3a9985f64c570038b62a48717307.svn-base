package com.samton.platform.task;

import com.samton.platform.framework.util.JsonUtil;
import com.samton.web.manage.task.service.ICustTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 定时分配任务
 * @Author: YangYangen
 * @Date: 2020/4/28 18:40
 * Copyright (c) 2020, Samton. All rights reserved
*/
@Component
public class CommonTask {

    @Resource
    private ICustTaskService iCustTaskService;

    private static final Logger log = LoggerFactory.getLogger(CommonTask.class);

    //每天0点5分触发一次执行
    @Scheduled(cron = "0 5 0 * * *")
    public void autoAllocateTask(){
        log.info("========================== 定时分配任务autoAllocateTask开始 ======================");
        try {
            String record = iCustTaskService.autoAllocateTask(2);
            log.info("========================== 定时分配任务autoAllocateTask结束{} ====================", record);
        } catch (Exception e) {
            log.error("========================== 定时分配任务autoAllocateTask失败！ ====================！", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(JsonUtil.stringify(2));
    }
}