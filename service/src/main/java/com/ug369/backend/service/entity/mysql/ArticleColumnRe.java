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
    private Long article;
    private Long column;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public Long getColumn() {
        return column;
    }

    public void setColumn(Long column) {
        this.column = column;
    }
}
