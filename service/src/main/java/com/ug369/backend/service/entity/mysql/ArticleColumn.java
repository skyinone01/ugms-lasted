package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "ug_article_column")
public class ArticleColumn {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createtime;
    private String title;
    private Boolean paymode;
    private String payitem1;
    private String payitem2;
    private String payitem3;
    private String picture;
    private String applypeople;
    private String applydetail;
    private Integer status;

    public String getApplypeople() {
        return applypeople;
    }

    public void setApplypeople(String applypeople) {
        this.applypeople = applypeople;
    }

    public String getApplydetail() {
        return applydetail;
    }

    public void setApplydetail(String applydetail) {
        this.applydetail = applydetail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPaymode() {
        return paymode;
    }

    public void setPaymode(Boolean paymode) {
        this.paymode = paymode;
    }

    public String getPayitem1() {
        return payitem1;
    }

    public void setPayitem1(String payitem1) {
        this.payitem1 = payitem1;
    }

    public String getPayitem2() {
        return payitem2;
    }

    public void setPayitem2(String payitem2) {
        this.payitem2 = payitem2;
    }

    public String getPayitem3() {
        return payitem3;
    }

    public void setPayitem3(String payitem3) {
        this.payitem3 = payitem3;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
