package com.samton.web.manage.order.enums;

/**
 * @ClassName: DemandTypeEnums
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-22 18:12
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public enum DemandTypeEnums {
    PROCUREMENT(1, "采购"),
    CLICK_FARMING(2, "刷单");
    private Integer type;
    private String desc;

    DemandTypeEnums(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public static String getDescByState(Integer type){
        DemandTypeEnums[] values = DemandTypeEnums.values();
        for (DemandTypeEnums value : values) {
            if(value.getType().equals(type)){
                return value.getDesc();
            }
        }
        return null;
    }

    public static Integer getStateByDesc(String desc){
        DemandTypeEnums[] values = DemandTypeEnums.values();
        for (DemandTypeEnums value : values) {
            if(value.getDesc().equals(desc.trim())){
                return value.getType();
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}