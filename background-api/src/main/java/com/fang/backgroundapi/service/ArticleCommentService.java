package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.ArticleComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fang.backgroundapi.pojo.VO.ArticleCommentVO;

import java.util.List;

/**
 * <p>
 * 博客评论表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface ArticleCommentService {

    public Integer publishComment(ArticleCommentVO articleCommentVO);

    public List<ArticleCommentVO> showComment(String articleId);

    public Integer deleteComment(String id, String authorId);

}
