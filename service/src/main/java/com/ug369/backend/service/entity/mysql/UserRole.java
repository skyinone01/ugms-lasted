package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Roy on 2017/3/10.
 */
@Entity
@Table(name = "ug_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 2406271872055393481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long role;
    private long user;

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

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
