package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.DO.Article;
import com.fang.backgroundapi.mapper.ArticleMapper;
import com.fang.backgroundapi.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-14
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
