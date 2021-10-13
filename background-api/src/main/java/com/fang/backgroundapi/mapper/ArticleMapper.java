package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;
import com.fang.backgroundapi.pojo.VO.SearchBlogVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 博客表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * @Description: 查找点赞最多(最受欢迎)的博客
     * @Author: Bernie_fang
     * @Since: 2021/9/12 21:29
     * @return: com.fang.backgroundapi.pojo.VO.MostPopularInfoVO
     **/
    public List<MostPopularInfoVO> queryMostPopular(@Param("quantity") Integer quantity);

    /**
     * @param quantity:
     * @Description: 查找最新的5条博文
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:03
     * @return: com.fang.backgroundapi.pojo.VO.MostPopularInfoVO
     **/
    public List<MostPopularInfoVO> queryRecent(@Param("quantity") Integer quantity);

    /**
     * @param keywordList:
     * @param currentPage:
     * @param size:
     * @Description: 搜索关键词。多字段、多参数查询
     * @Author: Bernie_fang
     * @Since: 2021/10/13 20:27
     * @return: java.util.List<com.fang.backgroundapi.pojo.VO.SearchBlogVO>
     **/
    public List<SearchBlogVO> searchBlog(@Param("keywordList") List<String> keywordList,
                                         @Param("currentPage") Integer currentPage,
                                         @Param("size") Integer size);

    /**
     * @Description:  搜索关键词-条数。多字段、多参数查询
     * @Author: Bernie_fang
     * @Since: 2021/10/13 20:29
     * @param keywordList:
     * @return: java.lang.Integer
     **/
    public Integer searchBlogCount(@Param("keywordList") List<String> keywordList);

}
