package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.pojo.BO.StatisticsBO;
import com.fang.backgroundapi.pojo.DO.BlogInfoDay;
import com.fang.backgroundapi.mapper.BlogInfoDayMapper;
import com.fang.backgroundapi.service.BlogInfoDayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客信息统计，每天 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-27
 */
@Service
public class BlogInfoDayServiceImpl extends ServiceImpl<BlogInfoDayMapper, BlogInfoDay> implements BlogInfoDayService {

    @Autowired
    private BlogInfoDayMapper blogInfoDayMapper;

    /**
     * Description: 统计查询 pageviews，likes，收藏量
     * @Author: Bernie_fang
     * @Since: 2022/2/27 17:21
     * @param authorId:
     * @return: void
     **/
    public BlogInfoDay statisticsPLC(String authorId){
        return blogInfoDayMapper.statisticsPLC(authorId);
    }

    public Integer statisticsSubscription(String authorId){
        return blogInfoDayMapper.statisticsSubscription(authorId);
    }

    public Integer statisticsComment(String authorId){
        return blogInfoDayMapper.statisticsComment(authorId);
    }

}
