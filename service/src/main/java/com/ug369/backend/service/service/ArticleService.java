package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.ArticleLabelRequest;
import com.ug369.backend.bean.bean.request.ArticleLevelRequest;
import com.ug369.backend.bean.bean.request.ArticleRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.Article;
import com.ug369.backend.service.entity.mysql.ArticleLabel;
import com.ug369.backend.service.entity.mysql.ArticleLevel;
import com.ug369.backend.service.repository.mysql.ArticleLabelRepository;
import com.ug369.backend.service.repository.mysql.ArticleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Roy on 2017/5/4.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleLabelRepository articleLabelRepository;

    @Autowired
    private com.ug369.backend.service.repository.mysql.ArticleLevelRepository articleLevelRepository;


    public PagedResult<ArticleRequest> getAll(PageRequest pageRequest) {

        return articleRepository.getDataPageBatch("Article.getAll", "Article.getCount", new HashMap<>(), pageRequest);

    }

    public Article findOne(long id) {

        return articleRepository.findOne(id);
    }

    public void delete(long id) {

        articleRepository.delete(id);
    }

    @Transactional
    public void createOrUpdate(ArticleRequest request) {
        Article one;
        if (request.getId()!=null && request.getId() != 0){
            one = articleRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            one = new Article();
            one.setCreatetime(new Date());
        }

        if (request.getStatus()!=null && (request.getStatus()==2 || request.getStatus()==3)){
            one.setStatus(request.getStatus());
            one.setApplydetail(request.getApplydetail());
            one.setApplypeople(request.getApplypeople());
        }else {
            one.setStatus(0);
        }
        if (!StringUtils.isEmpty(request.getTitle())){
            one.setTitle(request.getTitle());
        }
        if (!StringUtils.isEmpty(request.getContent())){
            one.setContent(request.getContent());
        }
        if (!StringUtils.isEmpty(request.getSummary())){
            one.setSummary(request.getSummary());
        }
        if (!StringUtils.isEmpty(request.getAuthor())){
            one.setAuthor(request.getAuthor());
        }
        if (request.getTypeid()!=null){
            one.setTypeid(request.getTypeid());
        }
        if (!StringUtils.isEmpty(request.getTypestr())){
            one.setTypestr(request.getTypestr());
        }
        if (!StringUtils.isEmpty(request.getSource())){
            one.setSource(request.getSource());
        }

        articleRepository.save(one);
    }


    public void changeStatus(long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        Article one = articleRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND);
        }
        if (op ==11){
            Article newone = new Article();
            newone.setStatus(0);
            newone.setTitle(one.getTitle());
            newone.setAuthor(one.getAuthor());
            newone.setCreatetime(new Date());
            newone.setContent(one.getContent());
            newone.setSource(one.getSource());
            newone.setTypeid(one.getTypeid());
            newone.setTypestr(one.getTypestr());
            newone.setSummary(one.getSummary());
            articleRepository.save(newone);
        }else {
            one.setStatus(op);
            articleRepository.save(one);
        }

    }

    public PagedResult<ArticleLabelRequest> getAllLabel(PageRequest pageRequest) {

        return articleLabelRepository.getDataPageBatch("Article.getAllLabel", "Article.getLabelCount", new HashMap<>(), pageRequest);

    }

    public void addOrUpdateLable(ArticleLabelRequest request){

        ArticleLabel label;
        if (request.getId() == null || request.getId().longValue() ==0){
            label = articleLabelRepository.findOne(request.getId());
            if (label == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            label = new ArticleLabel();
        }
        label.setLevel(request.getLevel());
        label.setName(request.getName());
        label.setLevelname(request.getLevelname());

        articleLabelRepository.save(label);
    }
    public void deleteLabel(Long id){
        if (id==null || id.longValue()==0){
            throw new UserException(UgmsStatus.BAD_REQUEST);
        }
        articleLabelRepository.delete(id);
    }

    public PagedResult<ArticleLevelRequest> getAllLevel(PageRequest pageRequest) {
        return articleLabelRepository.getDataPageBatch("Article.getAllLevel", "Article.getLevelCount", new HashMap<>(), pageRequest);

    }

    public void addOrUpdateLevel(ArticleLevelRequest request){

        ArticleLevel level;
        if (request.getId() == null || request.getId().longValue() ==0){
            level = articleLevelRepository.findOne(request.getId());
            if (level == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            level = new ArticleLevel();
        }
        level.setSort(request.getSort());
        level.setName(request.getName());

        articleLevelRepository.save(level);
    }
    public void deleteLevel(Long id){
        if (id==null || id.longValue()==0){
            throw new UserException(UgmsStatus.BAD_REQUEST);
        }
        articleLevelRepository.delete(id);
    }
}
