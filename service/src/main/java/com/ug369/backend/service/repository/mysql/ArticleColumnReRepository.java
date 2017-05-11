package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.ArticleColumnRe;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roy on 2017/3/27.
 */
@Repository
public interface ArticleColumnReRepository extends RDBRepository<ArticleColumnRe, Long> {

    List<ArticleColumnRe> findByColumn(long column);

    void deleteByColumn(Long column);

    void deleteByColumnAndArticle(Long column, Long article);
}
