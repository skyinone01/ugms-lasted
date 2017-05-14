package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;

/**
 * Created by Roy on 2017/3/27.
 */
@Entity
@Table(name = "ug_article_column_re")
public class ArticleColumnRe {
    private static final long serialVersionUID = 2406271872055393481L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long articleid;
    private Long columnid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public Long getColumnid() {
        return columnid;
    }

    public void setColumnid(Long columnid) {
        this.columnid = columnid;
    }
}
