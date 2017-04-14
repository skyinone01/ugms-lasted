package com.ug369.backend.bean.bean.response;

/**
 * Created by Administrator on 2017/3/18.
 */
public class PermissionResponse {

    private String stateRef;
    private String name;
    private boolean visible = true;
    private boolean deleteable;
    private boolean editable;
    private boolean operateable;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleteable() {
        return deleteable;
    }

    public void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isOperateable() {
        return operateable;
    }

    public void setOperateable(boolean operateable) {
        this.operateable = operateable;
    }
}
