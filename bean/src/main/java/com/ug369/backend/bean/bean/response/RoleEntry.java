package com.ug369.backend.bean.bean.response;

/**
 * Created by Administrator on 2017/3/18.
 */
public class RoleEntry {

    private long value;
    private String text;

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
