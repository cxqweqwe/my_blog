package com.fang.backgroundapi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.BlogInfoDay;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/27 14:27
 **/
@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @Autowired
    private BlogInfoDayServiceImpl blogInfoDayService;


    /**
     * Description:
     *
     * @Author: Bernie_fang
     * @Since: 2022/2/27 14:34
     * @return: void
     **/
    @Override
    // 秒 分 时 日 月 周几
    // @Scheduled(cron = "0 30 9 * * 7")// 每周日的9点30分执行
    public List<BlogInfoDay> statistics() {
        Date endDate = new Date();                      //当前时间
        Calendar ca = Calendar.getInstance();           //得到一个Calendar的实例
        ca.setTime(endDate);                            //设置时间为当前时间
        ca.add(Calendar.DATE, -7);              //前7天
        Date startDay = ca.getTime();                   //结果-7天前的时间


        QueryWrapper<BlogInfoDay> blogInfoDayQueryWrapper = new QueryWrapper<>();
        blogInfoDayQueryWrapper.ge("create_time", startDay)
                .lt("create_time", endDate)
                .orderByDesc("create_time");            //处于这几天的数据

        // Integer pageviews = 0;              // 浏览量
        // Integer likes = 0;                  // 点赞量
        // Integer subscription = 0;           // 订阅量
        // Integer comment = 0;                // 评论量
        // Integer collection = 0;             // 收藏量
        List<BlogInfoDay> infoDayList = blogInfoDayService.list(blogInfoDayQueryWrapper);
        return infoDayList;
    }

    /**
     * Description: 每日统计
     *
     * @Author: Bernie_fang
     * @Since: 2022/2/27 16:50
     * @return: void
     **/
    @Override
    @Scheduled(cron = "0 30 0 * * ?")// 每天的0点30分执行
    public void dailyStatistics() {

        List<SysUsers> sysUsers = sysUsersService.queryUser(0);
        for (SysUsers sysUser : sysUsers) {
            BlogInfoDay blogInfoDay = blogInfoDayService.statisticsPLC(sysUser.getAuthorId());
            if (ObjectUtil.isEmpty(blogInfoDay)){
                continue;       // 为null 则表示没用发表博客，跳出
            }
            Integer comment = blogInfoDayService.statisticsComment(sysUser.getAuthorId());
            Integer subscription = blogInfoDayService.statisticsSubscription(sysUser.getAuthorId());

            blogInfoDay.setAuthorId(sysUser.getAuthorId());
            blogInfoDay.setComment(comment);
            blogInfoDay.setSubscription(subscription);
            blogInfoDayService.save(blogInfoDay);
        }
    }


}
