package com.ug369.backend.outerapi.controller;

import com.ug369.backend.bean.base.request.PageRequest;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.base.response.PagedDataResponse;
import com.ug369.backend.bean.bean.request.ArticleLabelRequest;
import com.ug369.backend.bean.bean.request.ArticleLevelRequest;
import com.ug369.backend.bean.bean.request.ArticleRequest;
import com.ug369.backend.bean.exception.UgmsStatus;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.bean.result.PagedResult;
import com.ug369.backend.outerapi.annotation.PageDefault;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.entity.mysql.Article;
import com.ug369.backend.service.service.ArticleService;
import com.ug369.backend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Value("${ugms.static.file.path}")
    private String filePath;

    @Value("${ugms.static.url}")
    private String staticUrl;

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public PagedDataResponse<ArticleRequest> article(@PageDefault PageRequest pageRequest,
                                                     @RequestParam(value = "searchValue",required = false) String name) {
        PagedResult<ArticleRequest> users = articleService.getAll(pageRequest,name);

        return PagedDataResponse.of(users);
    }

    @RequestMapping(value = "/article4Column", method = RequestMethod.GET)
    public PagedDataResponse<ArticleRequest> article4Column(@PageDefault PageRequest pageRequest,
                                                            @RequestParam(value = "searchValue",required = false) String name,
                                                            @RequestParam(value = "id") Long id) {
        PagedResult<ArticleRequest> users = articleService.article4Column(pageRequest,name,id);

        return PagedDataResponse.of(users);
    }



    @RequestMapping(value = "/article/file", method = RequestMethod.POST)
    public DataResponse welcome(@PageDefault PageRequest pageRequest,
                                                     @RequestParam(value = "file",required = false) MultipartFile file) throws IOException, NoSuchAlgorithmException {
        String picUrl = null;
        if(file != null){
            if(!StringUtils.isPicture(file.getOriginalFilename())){
                throw  new UserException(UgmsStatus.BAD_REQUEST,"图片格式不符合要求");
            }
            ByteBuffer buffer = ByteBuffer.wrap(file.getBytes());
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(buffer);
            byte[] md5sum = messageDigest.digest();

            String name = StringUtils.MD5ToString(md5sum) +
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File img = new File(filePath + name);
            org.apache.commons.io.FileUtils.writeByteArrayToFile(img, file.getBytes(),false);
            picUrl = staticUrl+name;
        }

        return new DataResponse(picUrl);
    }


    @RequestMapping(value = "/article", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BasicResponse welcome( @RequestParam(value = "title",required = false) String title,
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


    @RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
    public BasicResponse one(@PathVariable("id") long id) {

        articleService.delete(id);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public DataResponse<Article> detailOne(@PathVariable("id") long id) {

        Article response = articleService.findOne(id);
        return new DataResponse<>(response);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.PUT)
    public BasicResponse updateOne(@PathVariable("id") Integer id,@RequestParam("op") int op) {

        articleService.changeStatus(id,op);
        return BasicResponse.success();
    }

    /**
     * level  level level level
     *
     */
    @RequestMapping(value = "/articleCategory", method = RequestMethod.GET)
    public PagedDataResponse<ArticleLevelRequest> level(@PageDefault PageRequest pageRequest) {
        PagedResult<ArticleLevelRequest> labels = articleService.getAllLevel(pageRequest);

        return PagedDataResponse.of(labels);
    }
    @RequestMapping(value = "/articleCategory", method = RequestMethod.PUT)
    public BasicResponse levelUpdate(@RequestBody ArticleLevelRequest request) {
        articleService.addOrUpdateLevel(request);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/articleCategory/{id}", method = RequestMethod.DELETE)
    public BasicResponse levelDel(@PathVariable("id") Long id) {
        articleService.deleteLevel(id);
        return BasicResponse.success();
    }


    /**
     * label  label label label
     *
     */
    @RequestMapping(value = "/articleLabel", method = RequestMethod.GET)
    public PagedDataResponse<ArticleLabelRequest> label(@PageDefault PageRequest pageRequest,@RequestParam("searchValue") String name) {
        PagedResult<ArticleLabelRequest> labels = articleService.getAllLabel(pageRequest,name);

        return PagedDataResponse.of(labels);
    }
    @RequestMapping(value = "/articleLabel", method = RequestMethod.PUT)
    public BasicResponse labelUpdate(@RequestBody ArticleLabelRequest request) {
        articleService.addOrUpdateLable(request);
        return BasicResponse.success();
    }

    @RequestMapping(value = "/articleLabel/{id}", method = RequestMethod.DELETE)
    public BasicResponse labelDel(@PathVariable("id") Long id) {
        articleService.deleteLabel(id);
        return BasicResponse.success();
    }

}
