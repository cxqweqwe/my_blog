package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.Subscription;
import com.fang.backgroundapi.mapper.SubscriptionMapper;
import com.fang.backgroundapi.service.SubscriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订阅记录表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-08
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService {

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    // @Override
    // public Integer insertSubscription(Subscription attention) {
    //     return null;
    // }

    // @Override
    // public Integer unSubscription(String subscribe, String beenSubscribed) {
    //     return null;
    // }

    @Override
    public Subscription findSubscription(String subscribe, String beenSubscribed) {
        QueryWrapper<Subscription> wrapper = new QueryWrapper<>();
        wrapper.eq("subscribe", subscribe)
                .eq("been_subscribed", beenSubscribed);
       return subscriptionMapper.selectOne(wrapper);
    }

    @Override
    public Integer querySubscribe(String subscribe) {
        return null;
    }

    @Override
    public Integer queryBeenSubscribed(String beenSubscribed) {
        return null;
    }

}
