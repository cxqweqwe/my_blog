package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.ArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.ArticleCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 博客评论表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    public List<ArticleCommentVO> queryComment(@Param("articleId") String articleId);

    public List<ArticleCommentVO> queryCommentGroup(@Param("firstCommentId") String firstCommentId);

}
