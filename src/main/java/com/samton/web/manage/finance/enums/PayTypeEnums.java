package com.samton.web.manage.finance.enums;

/**
 * @ClassName: PayTypeEnums
 * @Description: 支付类型
 * @Author: wuzhiqiang
 * @Date: 2020-04-20 15:34
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public enum PayTypeEnums {
    ALI(1, "支付宝"),
    WECHAT(2, "微信"),
    OTHER(5, "其他");

    private Integer type;
    private String desc;

    PayTypeEnums(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByType(Integer type){
        PayTypeEnums[] values = PayTypeEnums.values();
        for (PayTypeEnums value : values) {
            if(value.getType().equals(type)){
                return value.getDesc();
            }
        }
        return null;
    }
}