package com.samton.web.manage.finance.enums;

/**
 * @ClassName: ApproveStateEnums
 * @Description:
 * @Author: wuzhiqiang
 * @Date: 2020-04-20 15:55
 * @Copyright (c) 2020, Samton. All rights reserved
 **/
public enum ApproveStateEnums {
    /**待审核*/
    TO_BE_REVIEWED(0, "审核中"),
    APPROVED(1, "审核通过"),
    REJECTION_OF_REVIEW(2, "审核驳回")
    ;

    private Integer state;
    private String desc;

    ApproveStateEnums(Integer state, String desc){
        this.state = state;
        this.desc = desc;
    }

    public static String getDescByState(Integer state){
        ApproveStateEnums[] values = ApproveStateEnums.values();
        for (ApproveStateEnums value : values) {
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