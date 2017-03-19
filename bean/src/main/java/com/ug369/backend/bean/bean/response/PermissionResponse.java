package com.ug369.backend.bean.bean.response;

/**
 * Created by Administrator on 2017/3/18.
 */
public class PermissionResponse {

    private String stateRef;
    private boolean visible = true;

    public String getStateRef() {
        return stateRef;
    }

    public void setStateRef(String stateRef) {
        this.stateRef = stateRef;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
