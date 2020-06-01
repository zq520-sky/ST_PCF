package com.samton.web.manage.order.enums;

/**
 * @ClassName: OrderStatusEnums
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-22 18:12
 * @Copyright (c) 2020, Samton. All rights reserved
 **/

public enum OrderStatusEnums {
    UN_START(0, "未开始"),
    STARTING(1, "进行中"),
    FINISH(2, "已完成"),
    CANCEL(3, "已取消")
    ;

    private Integer state;
    private String desc;

    OrderStatusEnums(Integer state, String desc){
        this.state = state;
        this.desc = desc;
    }

    public static String getDescByState(Integer state){
        OrderStatusEnums[] values = OrderStatusEnums.values();
        for (OrderStatusEnums value : values) {
            if(value.getState().equals(state)){
                return value.getDesc();
            }
        }
        return null;
    }

    public Integer getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}