package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.service.impl.BlogInfoServiceImpl;
import com.fang.backgroundapi.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 博客信息统计表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/blogInfo")
@Api(tags = "博客信息相关接口")
@Validated   // Get请求方法需要这个注解配合
public class BlogInfoController extends BaseController {

    @Autowired
    private BlogInfoServiceImpl blogInfoService;

    @GetMapping("/likeBlog/{articleId}")
    @ApiOperation(value = "为博客点赞", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse likeBlog(@PathVariable("articleId") @NotBlank(message = "参数不能为空") String articleId) throws MyException {
        String authorId = super.getAuthorId();  //authorId从token拿
        blogInfoService.likeBlog(authorId, articleId);
        return ServerResponse.success();
    }

    @GetMapping("/unLikeBlog/{articleId}")
    @ApiOperation(value = "取消为为博客点赞", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse unLikeBlog(@PathVariable("articleId") @NotBlank(message = "参数不能为空") String articleId) throws MyException {
        String authorId = super.getAuthorId();  //authorId从token拿
        blogInfoService.unlikeBlog(authorId, articleId);
        return ServerResponse.success();
    }

    @GetMapping("/readBlog/{articleId}")
    @ApiOperation(value = "记录博客阅读量", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse readBlog(@PathVariable("articleId") @NotBlank(message = "参数不能为空") String articleId) {
        blogInfoService.readBlog(articleId);
        return ServerResponse.success();
    }

    @GetMapping("/blogInfo/{articleId}")
    @ApiOperation(value = "获取博客信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse blogInfo(@PathVariable("articleId") @NotBlank(message = "参数不能为空") String articleId) {
        BlogInfo blogInfo = blogInfoService.findBlogInfo(articleId);
        return ServerResponse.success(blogInfo);
    }




}

