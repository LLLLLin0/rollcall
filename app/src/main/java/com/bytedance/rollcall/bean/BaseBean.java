package com.bytedance.rollcall.bean;

import org.litepal.crud.LitePalSupport;

public class BaseBean {

    public BaseBean() {
    }

    public BaseBean(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
