package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.Article;
import com.fang.backgroundapi.mapper.ArticleMapper;
import com.fang.backgroundapi.pojo.DTO.ArticleDTO;
import com.fang.backgroundapi.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        wrapper.eq("article_id",articleId);
        Article article = articleMapper.selectOne(wrapper);
        return article;
    }

    @Override
    public PagingData findArticleAuthorId(String authorId, Integer currentPage, Integer size) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id",authorId);
        Page<Article> page = new Page<>(currentPage,size);
        articleMapper.selectPage(page, wrapper);
        PagingData data = new PagingData(page.getTotal(), page.getRecords());
        return data;
    }

    @Override
    public PagingData queryArticleAuthorId(Integer currentPage, Integer size) {
        Page<Article> page = new Page<>(currentPage,size);
        articleMapper.selectPage(page, null);
        PagingData data = new PagingData(page.getTotal(), page.getRecords());
        return data;
    }

    /* **************************************************/

    /**
     * @Description: 处理接收的新博客信息，写入数据库
     * @Author: Bernie_fang
     * @Since: 2021/8/25 21:46
     * @param articleDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse addArticle(ArticleDTO articleDTO) {

        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        Integer integer = this.insertArticle(article);
        return ServerResponse.success(integer);
    }

    /**
     * @Description: 处理接收的更新信息，更新到数据库
     * @Author: Bernie_fang
     * @Since: 2021/8/25 21:47
     * @param articleDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse updateArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        Integer integer = this.updateArticle(article);
        return ServerResponse.success(integer);
    }
}
