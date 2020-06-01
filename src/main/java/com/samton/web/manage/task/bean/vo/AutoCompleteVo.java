package com.samton.web.manage.task.bean.vo;

import com.samton.web.manage.task.bean.entity.TCustTask;

import java.io.Serializable;

/**
 * @Description: 买家账号模糊查询
 * @Author: ZhongShengbin
 * @Date: 2020/04/30 16:22
 * Copyright (c) 2019, Samton. All rights reserved
 */
public class AutoCompleteVo extends TCustTask implements Serializable {

    //显示内容
    private String label;
    //传值
    private Long id;

    private Long value;
    private String city;

    private String province;

    private String buyerName;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
