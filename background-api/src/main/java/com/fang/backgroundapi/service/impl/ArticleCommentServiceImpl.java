package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.ArticleComment;
import com.fang.backgroundapi.mapper.ArticleCommentMapper;
import com.fang.backgroundapi.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

}
