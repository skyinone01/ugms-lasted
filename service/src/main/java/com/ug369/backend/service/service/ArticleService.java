package com.ug369.backend.service.service;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.bean.request.ArticleColumnRequest;
import com.ug369.backend.bean.bean.request.ArticleLabelRequest;
import com.ug369.backend.bean.bean.request.ArticleLevelRequest;
import com.ug369.backend.bean.bean.request.ArticleRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.service.entity.mysql.*;
import com.ug369.backend.service.repository.mysql.ArticleColumnReRepository;
import com.ug369.backend.service.repository.mysql.ArticleColumnRepository;
import com.ug369.backend.service.repository.mysql.ArticleLabelRepository;
import com.ug369.backend.service.repository.mysql.ArticleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private ArticleColumnRepository articleColumnRepository;

    @Autowired
    private ArticleColumnReRepository articleColumnReRepository;


    @Autowired
    private com.ug369.backend.service.repository.mysql.ArticleLevelRepository articleLevelRepository;


    public PagedResult<ArticleRequest> getAll(PageRequest pageRequest,String name) {

        Map param = new HashMap<>();
        if (!StringUtils.isEmpty(name)){
            param.put("title","%"+name+"%");
        }


        return articleRepository.getDataPageBatch("Article.getAll", "Article.getCount", param, pageRequest);

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


    @Transactional
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

    @Transactional
    public PagedResult<ArticleLabelRequest> getAllLabel(PageRequest pageRequest,String name) {
        Map param = new HashMap<>();
        param.put("name","%"+name+"%");
        return articleLabelRepository.getDataPageBatch("Article.getAllLabel", "Article.getLabelCount",param , pageRequest);

    }

    @Transactional
    public void addOrUpdateLable(ArticleLabelRequest request){

        ArticleLabel label;
        if (request.getId() != null && request.getId().longValue() !=0){
            label = articleLabelRepository.findOne(request.getId());
            if (label == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            label = new ArticleLabel();
        }
        label.setLevel(request.getLevel());
        label.setName(request.getName());
        articleLabelRepository.save(label);
    }
    @Transactional
    public void deleteLabel(Long id){
        if (id==null || id.longValue()==0){
            throw new UserException(UgmsStatus.BAD_REQUEST);
        }
        articleLabelRepository.delete(id);
    }

    @Transactional
    public PagedResult<ArticleLevelRequest> getAllLevel(PageRequest pageRequest) {
        return articleLabelRepository.getDataPageBatch("Article.getAllLevel", "Article.getLevelCount", new HashMap<>(), pageRequest);

    }

    @Transactional
    public void addOrUpdateLevel(ArticleLevelRequest request){

        ArticleCategory level;
        if (request.getId() != null && request.getId().longValue() !=0){
            level = articleLevelRepository.findOne(request.getId());
            if (level == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            level = new ArticleCategory();
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

    @Transactional
    public PagedResult<ArticleColumn> getAllColumn(PageRequest pageRequest,String name) {

        Map param = new HashMap<>();
        if (!StringUtils.isEmpty(name)){
            param.put("title","%"+name+"%");
        }
        return articleLabelRepository.getDataPageBatch("Article.getAllColumn", "Article.getColumnCount", param, pageRequest);

    }
    @Transactional
    public ArticleColumnRequest findColumn(long id) {
        ArticleColumn one = articleColumnRepository.findOne(id);

        if (one != null){
            List<Article> byColumn = articleColumnReRepository.getData("Article.getArticleByColumn",one.getId());
            ArticleColumnRequest result = new ArticleColumnRequest();
            if (byColumn != null && byColumn.size()>0){
                byColumn.forEach(o-> result.setArticles(o.getTitle(),o.getId()));
            }

            result.setId(one.getId());
            result.setPaymode(one.getPaymode());
            result.setTitle(one.getTitle());
            result.setPicture(one.getPicture());
            if (one.getPaymode() ){
                String payitem1 = one.getPayitem1();
                String[] split = payitem1.split("_");
                result.setPayItem1(Integer.parseInt(split[0]),Integer.parseInt(split[1]));

                String payitem2 = one.getPayitem2();
                String[] split2 = payitem2.split("_");
                result.setPayItem2(Integer.parseInt(split2[0]),Integer.parseInt(split2[1]));

                String payitem3= one.getPayitem3();
                String[] split3 = payitem3.split("_");
                result.setPayItem3(Integer.parseInt(split3[0]),Integer.parseInt(split3[1]));

            }

            return result;
        }

        return null;
    }

    @Transactional
    public void deleteColumn(long id) {

        articleColumnReRepository.deleteByColumnid(id);
        articleColumnRepository.delete(id);
    }

    @Transactional
    public void changeColumnStatus(Long id, int op) {
        //审核 1 发布 2 启用 3 停用 4
        //1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
        ArticleColumn one = articleColumnRepository.findOne(id);
        if (one == null){
            throw  new UserException(UgmsStatus.NOT_FOUND);
        }
        if (op ==11){
            ArticleColumn newone = new ArticleColumn();
            newone.setStatus(0);
            newone.setTitle(one.getTitle());
            newone.setCreatetime(new Date());
            newone.setPayitem1(one.getPayitem1());
            newone.setPayitem2(one.getPayitem2());
            newone.setPayitem3(one.getPayitem3());
            newone.setPaymode(one.getPaymode());
            newone.setPicture(one.getPicture());
            articleColumnRepository.save(newone);

            List<ArticleColumnRe> byColumn = articleColumnReRepository.findByColumnid(one.getId());
            if (byColumn !=null && byColumn.size()>0){
                byColumn.forEach(o->{
                    ArticleColumnRe  articleColumnRe = new ArticleColumnRe();
                    articleColumnRe.setArticleid(o.getArticleid());
                    articleColumnRe.setColumnid(newone.getId());
                    articleColumnReRepository.save(articleColumnRe);
                });
            }
        }else {
            one.setStatus(op);
            articleColumnRepository.save(one);
        }
    }

    @Transactional
    public void createOrUpdateColumn(ArticleColumn request) {

        ArticleColumn one;
        if (request.getId()!=null && request.getId() != 0){
            one = articleColumnRepository.findOne(request.getId());
            if (one == null){
                throw new UserException(UgmsStatus.NOT_FOUND);
            }
        }else {
            one = request;
            one.setCreatetime(new Date());
        }

        if (request.getStatus()!=null && (request.getStatus()==2 || request.getStatus()==3)){
            one.setStatus(request.getStatus());
            one.setApplydetail(request.getApplydetail());
            one.setApplypeople(request.getApplypeople());
        }else {
            one.setStatus(0);
        }
        if (request.getPaymode()){
            one.setPaymode(true);
            if (!StringUtils.isEmpty(request.getTitle())){
                one.setTitle(request.getTitle());
            }
            if (!StringUtils.isEmpty(request.getPayitem2())){
                one.setPayitem2(request.getPayitem2());
            }
            if (!StringUtils.isEmpty(request.getPayitem3())){
                one.setPayitem3(request.getPayitem3());
            }
        }
        if (!StringUtils.isEmpty(request.getPicture())){
            one.setPicture(request.getPicture());
        }


        articleColumnRepository.save(one);

    }

    public void addArticleToColumn(Long column, Long article,boolean addable) {
        if (addable){
            ArticleColumnRe one = new ArticleColumnRe();
            one.setArticleid(article);
            one.setColumnid(column);
            articleColumnReRepository.save(one);
        }else {
            ArticleColumnRe one = articleColumnReRepository.findByColumnidAndArticleid(column, article);
            articleColumnReRepository.delete(one);
        }
    }

    public PagedResult<ArticleRequest> article4Column(PageRequest pageRequest, String name,Long columnid ) {
        Map param = new HashMap<>();
        param.put("columnid",columnid);
        if (!StringUtils.isEmpty(name)){
            param.put("title","%"+name+"%");
        }


        return articleRepository.getDataPageBatch("Article.article4Column", "Article.article4ColumnCount", param, pageRequest);
    }
}
