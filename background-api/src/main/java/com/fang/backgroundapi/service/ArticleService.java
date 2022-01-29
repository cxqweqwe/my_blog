package com.fang.backgroundapi.service;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.pojo.DO.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;

import java.util.List;

/**
 * <p>
 * 博客表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
public interface ArticleService {

    /**
     * @Description: 新增
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:26
     * @param article:
     * @return: java.lang.Integer
     **/
    public Integer insertArticle(Article article);

    /**
     * @Description: 修改
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @param article:
     * @return: java.lang.Integer
     **/
    public Integer updateArticle(Article article);

    /**
     * @Description: 删除
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @param articleId:
     * @return: java.lang.Integer
     **/
    public Integer deleteArticle(String articleId);

    /**
     * @Description: 根据id查找一篇
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @param articleId:
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public Article findArticleArticleId(String articleId);

    /**
     * @Description: 根据作者id查找一堆
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @param authorId:
     * @param currentPage: 当前页

     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData findArticleAuthorId(String authorId, Integer currentPage, Integer size);

    /**
     * @Description: 查找点赞最多的5条记录。做Popular推荐
     * @Author: Bernie_fang
     * @Since: 2021/9/12 21:13
     * @param size: 查询数量
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public List<MostPopularInfoVO> queryMostPopular(Integer size);

    /**
     * @Description: 查找最新的的5条记录。做Recent推荐
     * @Author: Bernie_fang
     * @Since: 2021/9/12 21:13
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public List<MostPopularInfoVO> queryRecent();

    /* ************************************************************************************** */

    /**
     * @Description: 查找全部，管理员以上的级别使用
     * @Author: Bernie_fang
     * @Since: 2021/8/25 15:27
     * @param currentPage: 当前页
     * @param size: 页内容量
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData queryArticleAuthorId(Integer currentPage, Integer size);

    /**
     * Description:
     * @Author: Bernie_fang
     * @Since: 2022/1/7 22:01
     * @param currentPage:
     * @param size:
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData getPersonal(Integer currentPage, Integer size, String authorId);

    /**
     * Description: 查找点赞最多的几个
     * @Author: Bernie_fang
     * @Since: 2022/1/29 21:20
     * @param currentPage: -1表示不分页
     * @param size:
     * @return: com.fang.backgroundapi.common.PagingData
     **/
    public PagingData mostPageViews(Integer currentPage, Integer size);

}
