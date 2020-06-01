package com.samton.web.manage.task.bean.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;

/**
 * @Description: 批量导入报告完成状态Vo
 * @Author: ZhongShengbin
 * @Date: 2020/04/27 15:02
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class TaskSettingOkExcelVo {

    @Excel(name="任务编号")
    private String taskCode;

    @Excel(name="卡号")
    private String cardsCode;

    @Excel(name="订单数量")
    private Integer orderNum;

    @Excel(name="税收")
    private BigDecimal tax;

    @Excel(name="订单总额")
    private BigDecimal orderMoney;

    @Excel(name="任务完成时间状态")
    private String taskFinishNote;

    @Excel(name="任务状态")
    private String taskState;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getCardsCode() {
        return cardsCode;
    }

    public void setCardsCode(String cardsCode) {
        this.cardsCode = cardsCode;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getTaskFinishNote() {
        return taskFinishNote;
    }

    public void setTaskFinishNote(String taskFinishNote) {
        this.taskFinishNote = taskFinishNote;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
