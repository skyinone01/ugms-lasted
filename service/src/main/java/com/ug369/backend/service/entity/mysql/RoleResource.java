package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Roy on 2017/3/10.
 */
@Entity
@Table(name = "ug_role_resource")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 2406271872055393481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long role;
    private long resource;
    private Boolean deleteable;
    private Boolean editable;
    private Boolean requestable;
    private Boolean cancelable;
    private Boolean applyable;
    private Boolean upable;
    private Boolean downable;
    private Boolean copyable;
    private Boolean releaseable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getResource() {
        return resource;
    }

    public void setResource(long resource) {
        this.resource = resource;
    }

    public Boolean getDeleteable() {
        return deleteable;
    }

    public void setDeleteable(Boolean deleteable) {
        this.deleteable = deleteable;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getRequestable() {
        return requestable;
    }

    public void setRequestable(Boolean requestable) {
        this.requestable = requestable;
    }

    public Boolean getCancelable() {
        return cancelable;
    }

    public void setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
    }

    public Boolean getApplyable() {
        return applyable;
    }

    public void setApplyable(Boolean applyable) {
        this.applyable = applyable;
    }

    public Boolean getUpable() {
        return upable;
    }

    public void setUpable(Boolean upable) {
        this.upable = upable;
    }

    public Boolean getDownable() {
        return downable;
    }

    public void setDownable(Boolean downable) {
        this.downable = downable;
    }

    public Boolean getCopyable() {
        return copyable;
    }

    public void setCopyable(Boolean copyable) {
        this.copyable = copyable;
    }

    public Boolean getReleaseable() {
        return releaseable;
    }

    public void setReleaseable(Boolean releaseable) {
        this.releaseable = releaseable;
    }
}
