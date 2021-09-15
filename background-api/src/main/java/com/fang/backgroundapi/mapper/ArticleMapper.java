package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.MostPopularInfoVO;
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
     * @Description: 查找最新的5条博文
     * @Author: Bernie_fang
     * @Since: 2021/9/12 22:03
     * @param quantity:
     * @return: com.fang.backgroundapi.pojo.VO.MostPopularInfoVO
     **/
    public List<MostPopularInfoVO> queryRecent(@Param("quantity") Integer quantity);


}
