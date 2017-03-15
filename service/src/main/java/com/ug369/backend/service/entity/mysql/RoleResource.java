package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Roy on 2017/3/10.
 */
@Entity
@Table(name = "demo_role_res")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 2406271872055393481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long role;
    private long resource;

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
}
