package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.ArticleRequest;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.Article;
import com.ug369.backend.service.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
public class ArticleColumnController {

    @Autowired
    private ArticleService articleService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    @RequestMapping(value = "/articleColumn", method = RequestMethod.GET)
    public PagedDataResponse<ArticleRequest> articleColumns(@PageDefault PageRequest pageRequest) {
        PagedResult<ArticleRequest> users = articleService.getAll(pageRequest);

        return PagedDataResponse.of(users);
    }

    @RequestMapping(value = "/articleColumn", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse articleColumn( @RequestParam(value = "title",required = false) String title,
                                  @RequestParam(value = "id") long id,
                                  @RequestParam(value = "content",required = false) String content,
                                  @RequestParam(value = "summary",required = false) String summary,
                                  @RequestParam(value = "typeid",required = false) Integer typeid,
                                  @RequestParam(value = "typestr",required = false) String typestr,
                                  @RequestParam(value = "status",required = false) Integer status,
                                  @RequestParam(value = "source",required = false) String source,
                                  @RequestParam(value = "author",required = false) String author,
                                  @RequestParam(value = "applydetail",required = false) String applydetail,
                                  @UserInjected WebUser user) throws NoSuchAlgorithmException, IOException, ParseException {

        ArticleRequest request = new ArticleRequest();

//        bannerRequest.setContent(title);
        request.setId(id);
        request.setTitle(title);
        request.setSummary(summary);
        request.setSource(source);
        request.setStatus(status);
        request.setApplydetail(applydetail);
        request.setApplypeople(user.getName());
        request.setContent(content);
        request.setAuthor(author);
        articleService.createOrUpdate(request);
        return BasicResponse.success();
    }


    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.DELETE)
    public BasicResponse one(@PathVariable("id") long id) {

        articleService.delete(id);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.GET)
    public DataResponse<Article> detailOne(@PathVariable("id") long id) {

        Article response = articleService.findOne(id);
        return new DataResponse<>(response);
    }

    @RequestMapping(value = "/articleColumn/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") Integer id,@RequestParam("op") int op) {

        articleService.changeStatus(id,op);
        return BasicResponse.success();
    }

}
