package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 2017/4/19.
 */
@Entity
@Table(name = "ug_monitor")
public class SystemMonitor {

    private static final long serialVersionUID = 2406271872055393481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date datepoint;
    private double useage;
    private double total;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatepoint() {
        return datepoint;
    }

    public void setDatepoint(Date datepoint) {
        this.datepoint = datepoint;
    }

    public double getUseage() {
        return useage;
    }

    public void setUseage(double useage) {
        this.useage = useage;
    }
}
