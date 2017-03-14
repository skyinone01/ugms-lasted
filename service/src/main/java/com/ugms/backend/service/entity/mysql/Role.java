package com.ugms.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Roy on 2017/3/10.
 */
@Entity
@Table(name = "demo_role")
public class Role implements Serializable{


	private static final long serialVersionUID = -8566197653568234573L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 32)
    private String name;

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
}
