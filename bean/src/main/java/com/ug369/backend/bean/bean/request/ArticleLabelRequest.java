package com.ug369.backend.bean.bean.request;

/**
 * Created by Roy on 2017/5/4.
 */
public class ArticleLabelRequest {

    private Long id;
    private String name;
    private Integer level;
    private String levelname;

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
