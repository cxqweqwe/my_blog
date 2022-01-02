package com.fang.backgroundapi.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.druid.sql.parser.Keywords;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.Article;
import com.fang.backgroundapi.mapper.ArticleMapper;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;
import com.fang.backgroundapi.pojo.VO.PostShowVO;
import com.fang.backgroundapi.pojo.VO.SearchBlogVO;
import com.fang.backgroundapi.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer insertArticle(Article article) {
        int insert = articleMapper.insert(article);
        return insert;
    }

    @Override
    public Integer updateArticle(Article article) {
        int update = articleMapper.updateById(article);
        return update;
    }

    @Override
    public Integer deleteArticle(String articleId) {
        int delete = articleMapper.deleteById(articleId);
        return delete;
    }

    @Override
    public Article findArticleArticleId(String articleId) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        Article article = articleMapper.selectOne(wrapper);
        return article;
    }

    @Override
    public PagingData findArticleAuthorId(String authorId, Integer currentPage, Integer size) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", authorId);
        Page<Article> page = new Page<>(currentPage, size);
        articleMapper.selectPage(page, wrapper);
        PagingData data = new PagingData(page.getTotal(), page.getRecords());
        return data;
    }

    /**
     * @param currentPage: 当前页
     * @param size:        页内容量
     * @Description: 查找全部，管理员以上的级别使用
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    @Override
    public PagingData queryArticleAuthorId(Integer currentPage, Integer size) {
        Page<Article> page = new Page<>(currentPage, size);
        articleMapper.selectPage(page, null);
        PagingData data = new PagingData(page.getTotal(), page.getRecords());
        return data;
    }

    @Override
    public List<MostPopularInfoVO> queryMostPopular(Integer size) {
        List<MostPopularInfoVO> popular = articleMapper.queryMostPopular(size);
        return popular;
    }

    @Override
    public List<MostPopularInfoVO> queryRecent() {
        List<MostPopularInfoVO> recent = articleMapper.queryRecent(4);
        return recent;
    }

    /* **************************************************/

    /**
     * @param articleDTO:
     * @Description: 处理接收的新博客信息，写入数据库
     * @Author: Bernie_fang
     * @Since: 2021/8/25 21:46
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse addArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        String simpleUUID = IdUtil.simpleUUID();
        article.setArticleId(simpleUUID);
        Integer integer = this.insertArticle(article);
        return ServerResponse.success(simpleUUID);
    }

    /**
     * @param articleDTO:
     * @Description: 处理接收的更新信息，更新到数据库
     * @Author: Bernie_fang
     * @Since: 2021/8/25 21:47
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse updateArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        Integer integer = this.updateArticle(article);
        return ServerResponse.success(integer);
    }

    public PagingData searchBlog(String keyword, Integer currentPage, Integer size) {
        currentPage = (currentPage - 1) * size;
        String[] keywords = keyword.trim().split("\\s+");
        List<String> list = Arrays.asList(keywords);
        List<SearchBlogVO> searchBlogVOS = articleMapper.searchBlog(list, currentPage, size);
        for (SearchBlogVO blogVO : searchBlogVOS) {
            System.out.println(blogVO);
        }
        Integer total = articleMapper.searchBlogCount(list);
        PagingData data = new PagingData(Long.valueOf(total), searchBlogVOS);
        return data;
    }

    /**
     * @param curPage:
     * @param size:
     * @Description: 查找点赞最多(最受欢迎)的博客, 分页
     * @Author: Bernie_fang
     * @Since: 2021/10/21 14:10
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData queryMostPopularPaging(Integer curPage, Integer size) {
        curPage = (curPage - 1) * size;
        List<MostPopularInfoVO> popular = articleMapper.queryMostPopularPaging(curPage, size);
        Integer total = articleMapper.queryMostPopularCount();
        PagingData data = new PagingData(Long.valueOf(total), popular);
        return data;
    }

    /**
     * Description: 查找最新的
     * @Author: Bernie_fang
     * @Since: 2021/12/25 15:09
     * @param curPage: -1表示不分页
     * @param size:
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData latestPosts(Integer curPage, Integer size){
        curPage = (curPage - 1) * size;
        PagingData pagingData = new PagingData();
        if (curPage != null && curPage != -1){
            // 做分页
            Integer integer = articleMapper.latestPostsCount();
            pagingData.setTotal(Long.valueOf(integer));
        }
        List<PostShowVO> postShowVOList = articleMapper.latestPosts(curPage, size);
        pagingData.setData(postShowVOList);
        return pagingData;
    }
/**
     * Description: 被收藏最多的
     * @Author: Bernie_fang
     * @Since: 2021/12/25 15:09
     * @param curPage: -1表示不分页
     * @param size:
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData mostCollection(Integer curPage, Integer size){
        curPage = (curPage - 1) * size;
        PagingData pagingData = new PagingData();
        if (curPage != null && curPage != -1){
            // 做分页
            Integer integer = articleMapper.latestPostsCount();//此方法可在这重复使用
            pagingData.setTotal(Long.valueOf(integer));
        }
        List<PostShowVO> postShowVOList = articleMapper.mostCollection(curPage, size);
        pagingData.setData(postShowVOList);
        return pagingData;
    }


}
