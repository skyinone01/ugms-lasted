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
    private boolean upable;
    private boolean downable;
    private boolean applyable;
    private boolean cancelable;
    private boolean copyable;
    private boolean releaseable;
    private boolean requestable;

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

    public boolean isUpable() {
        return upable;
    }

    public void setUpable(boolean upable) {
        this.upable = upable;
    }

    public boolean isDownable() {
        return downable;
    }

    public void setDownable(boolean downable) {
        this.downable = downable;
    }

    public boolean isApplyable() {
        return applyable;
    }

    public void setApplyable(boolean applyable) {
        this.applyable = applyable;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isCopyable() {
        return copyable;
    }

    public void setCopyable(boolean copyable) {
        this.copyable = copyable;
    }

    public boolean isReleaseable() {
        return releaseable;
    }

    public void setReleaseable(boolean releaseable) {
        this.releaseable = releaseable;
    }

    public boolean isRequestable() {
        return requestable;
    }

    public void setRequestable(boolean requestable) {
        this.requestable = requestable;
    }
}
