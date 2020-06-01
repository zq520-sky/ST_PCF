package com.samton.platform.pm.bean.vo;
import java.io.Serializable;
public class AutoCompleteVo implements Serializable {

    //显示内容
    private String label;
    //传值
    private Long id;

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
}

