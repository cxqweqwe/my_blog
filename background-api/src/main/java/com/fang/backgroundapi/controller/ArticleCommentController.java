package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.pojo.VO.ArticleCommentVO;
import com.fang.backgroundapi.service.impl.ArticleCommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 博客评论表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/articleComment")
@Validated   // Get请求方法需要这个注解配合
@Api(tags = "博客评论相关接口")
public class ArticleCommentController extends BaseController {

    @Autowired
    private ArticleCommentServiceImpl articleCommentService;

    @PostMapping("/publish")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "发表评论", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse publishComment(@RequestBody @Valid ArticleCommentVO articleCommentVO) throws MyException {
        String authorId = super.getAuthorId();
        articleCommentVO.setAuthorId(authorId);
        articleCommentService.publishComment(articleCommentVO);
        return ServerResponse.success();
    }

    @GetMapping("/show/{articleId}")
    @ApiOperation(value = "展示评论", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse showComment(@PathVariable("articleId")  @NotBlank(message = "参数不为空")String articleId) {
        List<ArticleCommentVO> comments = articleCommentService.showComment(articleId);
        return ServerResponse.success(comments);
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "删除评论", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse deleteComment(@PathVariable("id") @NotBlank(message = "参数不为空") String id) throws MyException {
        String authorId = super.getAuthorId();
        articleCommentService.deleteComment(id, authorId);
        return ServerResponse.success();
    }




}
