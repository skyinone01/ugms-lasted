package com.ug369.backend.bean.bean.response;

/**
 * Created by Administrator on 2017/3/19.
 */
public class ResourceEntryUGMS {

    private long id;
    private String name;
    private String url;
    private String state;
    private String description;
    private boolean visible;
    private boolean delete;
    private boolean edit;
    private boolean operate;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isOperate() {
        return operate;
    }

    public void setOperate(boolean operate) {
        this.operate = operate;
    }
}
