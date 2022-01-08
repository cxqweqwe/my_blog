package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.Article;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;
import com.fang.backgroundapi.pojo.VO.SearchBlogVO;
import com.fang.backgroundapi.service.impl.ArticleCommentServiceImpl;
import com.fang.backgroundapi.service.impl.ArticleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.table.AbstractTableModel;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 博客表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
@RestController
@RequestMapping("/article")
@Validated   // Get请求方法需要这个注解配合
@Api(tags = "博客操作相关接口")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleServiceImpl articleService;

    @PostMapping("/release")
    @ApiOperation(value = "博客发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse releaseArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        return articleService.addArticle(articleDTO);
    }

    @PostMapping("/update")
    @ApiOperation(value = "博客发布", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updateArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        return articleService.updateArticle(articleDTO);
    }

    @GetMapping("/detail/{articleId}")
    @ApiOperation(value = "获取博客详情", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse articleDetail(@PathVariable("articleId") @NotBlank(message = "博文ID不能为空")String articleId) {
        Article article = articleService.findArticleArticleId(articleId);
        return ServerResponse.success(article);
    }

    @GetMapping("/delete/{articleId}")
    @ApiOperation(value = "删除博客", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse deleteArticle(@PathVariable("articleId") @NotBlank(message = "博文ID不能为空")String articleId) {
        Integer delete = articleService.deleteArticle(articleId);
        return ServerResponse.success(delete);
    }

    /**
     * @Description: 查找最受欢迎(点赞最多)的的博客
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:10
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/popular/{size}")
    @ApiOperation(value = "查找最受欢迎(点赞最多)的的博客", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryMostPopular(@PathVariable("size") @ApiParam(value = "需要查询多少条") Integer size) {
        List<MostPopularInfoVO> popular = articleService.queryMostPopular(size);
        return ServerResponse.success(popular);
    }

    /**
     * @Description: 查找最受欢迎(点赞最多)的的博客
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:10
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/popular/paging")
    @ApiOperation(value = "查找最受欢迎(点赞最多)的的博客", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryMostPopularPaging(@RequestParam @ApiParam(value = "当前页") Integer curPage,
                                                 @RequestParam @ApiParam(value = "需要查询多少条") Integer size) {
        PagingData pagingData = articleService.queryMostPopularPaging(curPage, size);
        return ServerResponse.success(pagingData);
    }

    /**
     * @Description: 查找最近发布的博客
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:10
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/recent")
    @ApiOperation(value = "查找最近发布的博客", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryRecent() {
        List<MostPopularInfoVO> recent = articleService.queryRecent();
        return ServerResponse.success(recent);
    }

    /**
     * @Description: 这里是对应博客搜索
     * @Author: Bernie_fang
     * @Since: 2021/10/13 15:09
     * @param keyword:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/search/{keyword}/{currentPage}/{size}")
    @ApiOperation(value = "搜索博客名字、简介、标签含有的关键词", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse searchBlog(@PathVariable("keyword") @NotBlank(message = "关键词不能为空") String keyword,
                                     @PathVariable("currentPage") Integer currentPage,
                                     @PathVariable("size") Integer size) {

        PagingData data = articleService.searchBlog(keyword, currentPage, size);
        return ServerResponse.success(data);
    }


    @GetMapping("/latestPosts/{currentPage}/{size}")
    @ApiOperation(value = "最新帖子", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse latestPosts(@PathVariable("currentPage") Integer currentPage,
                                     @PathVariable("size") Integer size) {
        PagingData data = articleService.latestPosts(currentPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/mostCollection/{currentPage}/{size}")
    @ApiOperation(value = "被收藏最多", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse mostCollection(@PathVariable("currentPage") Integer currentPage,
                                      @PathVariable("size") Integer size) {
        PagingData data = articleService.mostCollection(currentPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/personal/{currentPage}/{size}/{authorId}")
    @ApiOperation(value = "被收藏最多", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse getPersonal(@PathVariable("currentPage") Integer currentPage,
                                      @PathVariable("size") Integer size,
                                      @PathVariable("authorId") String authorId){
        PagingData data = articleService.getPersonal(currentPage, size, authorId);
        return ServerResponse.success(data);
    }



}

