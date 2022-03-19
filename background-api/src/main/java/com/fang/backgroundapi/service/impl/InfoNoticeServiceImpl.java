package com.fang.backgroundapi.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.pojo.DO.InfoNotice;
import com.fang.backgroundapi.mapper.InfoNoticeMapper;
import com.fang.backgroundapi.pojo.DO.Subscription;
import com.fang.backgroundapi.service.InfoNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 信息通知记录表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-03-19
 */
@Service
public class InfoNoticeServiceImpl extends ServiceImpl<InfoNoticeMapper, InfoNotice> implements InfoNoticeService {

    @Autowired
    private InfoNoticeMapper infoNoticeMapper;

    @Autowired
    private SubscriptionServiceImpl subscriptionService;

    @Override
    public Integer insertInfoNoticeList(List<InfoNotice> infoNoticeList) {
        return infoNoticeMapper.insertInfoNoticeList(infoNoticeList);
    }

    /**
     * Description: 标记所有未读信息未已读
     * @Author: Bernie_fang
     * @Since: 2022/3/19 22:49
     * @param beNotified:
     * @return: java.lang.Integer
     **/
    @Override
    public Integer updateRead(String beNotified) {
        UpdateWrapper<InfoNotice> wrapper = new UpdateWrapper<>();
        wrapper.set("is_read", 1).eq("be_notified", beNotified);
        return infoNoticeMapper.update(null, wrapper);
    }

    @Override
    public Long queryUnreadCount(String beNotified) {
        return infoNoticeMapper.queryUnreadCount(beNotified);
    }

    @Override
    public List<InfoNotice> queryInfoNotice(String beNotified) {
        QueryWrapper<InfoNotice> wrapper = new QueryWrapper<>();
        wrapper.eq("be_notified", beNotified);
        return infoNoticeMapper.selectList(wrapper);
    }

    public List<InfoNotice> readAndQueryInfoNotice(String beNotified){
        this.updateRead(beNotified);
        List<InfoNotice> infoNoticeList = this.queryInfoNotice(beNotified);
        return infoNoticeList;
    }

    public void handleReleaseArticle(String authorId, String articleId, String title) {
        // 查询所有的订阅人
        List<Subscription> subscriptions = subscriptionService.querySubscribe(authorId);
        List<InfoNotice> insertList = new ArrayList<>();
        Date date = new Date();
        // 构建通知信息并写入
        if (CollectionUtils.isEmpty(subscriptions)){
            return;
        }
        for (Subscription subscription : subscriptions) {
            InfoNotice notice = new InfoNotice();
            notice.setId(IdUtil.simpleUUID());
            notice.setBeNotified(subscription.getSubscribe());// 要通知的人
            notice.setArticleId(articleId);
            notice.setAuthorId(authorId);
            notice.setTitle(title);
            notice.setCreateTime(date);
            notice.setModifiedTime(date);
            insertList.add(notice);
        }
        if (insertList.size() < 0){
            return;
        }
        this.insertInfoNoticeList(insertList);
    }

}
