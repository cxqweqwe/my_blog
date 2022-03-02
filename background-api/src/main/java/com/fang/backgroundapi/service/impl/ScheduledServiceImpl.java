package com.fang.backgroundapi.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.BlogInfoDay;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/27 14:27
 **/
@Service
@Slf4j
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @Autowired
    private BlogInfoDayServiceImpl blogInfoDayService;

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private AsyncService asyncService;

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
    public void statistics() {
        List<UserInfo> userInfos = userInfoService.queryUserInfo(0);//查询正常用户的信息
        for (UserInfo userInfo : userInfos) {
            if (StringUtils.isNotEmpty(userInfo.getEmail().getValue())){
                ArrayList<List<Object>> dataSet = this.getData(userInfo.getAuthorId());
                if (dataSet == null){
                    continue;
                }
                String jsonStr = JSONUtil.toJsonStr(dataSet);
                System.out.println("=========================> "+jsonStr);
                asyncService.sendEmailStatistics(dataSet, userInfo.getEmail().getValue());
                log.info("发送数据成功 =========> {}", userInfo.getEmail().getValue());
            }
        }

    }

    public ArrayList<List<Object>> getData(String authorId){
        Date endDate = new Date();                      //当前时间
        Calendar ca = Calendar.getInstance();           //得到一个Calendar的实例
        ca.setTime(endDate);                            //设置时间为当前时间
        ca.add(Calendar.DATE, -7);              //前7天
        Date startDay = ca.getTime();                   //结果-7天前的时间


        QueryWrapper<BlogInfoDay> blogInfoDayQueryWrapper = new QueryWrapper<>();
        blogInfoDayQueryWrapper.ge("create_time", startDay)
                .lt("create_time", endDate)
                .eq("author_id", authorId)
                .orderByAsc("create_time");            //处于这几天的数据

        List<BlogInfoDay> infoDayList = blogInfoDayService.list(blogInfoDayQueryWrapper);
        if (infoDayList == null){
            return null;
        }

        ArrayList<List<Object>> dataSet = new ArrayList<>();
        List<Object> arrayList = Arrays.asList("number", "type", "createTime");
        dataSet.add(arrayList);

        for (BlogInfoDay infoDay : infoDayList) {
            // subscription 被订阅量
            ArrayList<Object> subscriptionList = new ArrayList<>();
            subscriptionList.add(infoDay.getSubscription());
            subscriptionList.add("subscription");
            subscriptionList.add(infoDay.getCreateTime());
            dataSet.add(subscriptionList);
            // collection 收藏量
            ArrayList<Object> collectionList = new ArrayList<>();
            collectionList.add(infoDay.getCollection());
            collectionList.add("collection");
            collectionList.add(infoDay.getCreateTime());
            dataSet.add(collectionList);
            // pageviews 查阅量
            ArrayList<Object> pageviewsList = new ArrayList<>();
            pageviewsList.add(infoDay.getPageviews());
            pageviewsList.add("pageviews");
            pageviewsList.add(infoDay.getCreateTime());
            dataSet.add(pageviewsList);
            // comment 评论量
            ArrayList<Object> commentList = new ArrayList<>();
            commentList.add(infoDay.getComment());
            commentList.add("comment");
            commentList.add(infoDay.getCreateTime());
            dataSet.add(commentList);
            // likes 点赞量
            ArrayList<Object> likesList = new ArrayList<>();
            likesList.add(infoDay.getLikes());
            likesList.add("likes");
            likesList.add(infoDay.getCreateTime());
            dataSet.add(likesList);
        }
        return dataSet;
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
