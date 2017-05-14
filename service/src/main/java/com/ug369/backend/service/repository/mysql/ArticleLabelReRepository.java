package com.ug369.backend.service.repository.mysql;

import com.ug369.backend.service.entity.mysql.ArticleLabelRe;
import com.ug369.backend.service.repository.rdbsupport.RDBRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roy on 2017/3/27.
 */
@Repository
public interface ArticleLabelReRepository extends RDBRepository<ArticleLabelRe, Long> {


    List<ArticleLabelRe> findByArticle(Long id);

    ArticleLabelRe findByArticleAndLabel(Long article, Long label);
}
