package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 博客信息统计表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {

    public Integer likeBlog(@Param("articleId") String articleId);

    public Integer unLikeBlog(@Param("articleId") String articleId);

    public Integer readBlog(@Param("articleId") String articleId);

}
