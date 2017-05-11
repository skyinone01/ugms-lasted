package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "ug_article")
public class Article {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createtime;
    private String title;
    private String content;
    private String source;
    private String author;
    private String typestr;
    private Integer typeid;
    private Integer status;
    private String summary;
    private String applypeople;
    private String applydetail;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTypestr() {
        return typestr;
    }

    public void setTypestr(String typestr) {
        this.typestr = typestr;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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
}
