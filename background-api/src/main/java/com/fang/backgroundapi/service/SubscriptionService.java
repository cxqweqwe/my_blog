package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订阅记录表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-08
 */
public interface SubscriptionService extends IService<Subscription> {

    // /**
    //  * Description: 添加关注记录
    //  * @Author: Bernie_fang
    //  * @Since: 2022/2/8 15:56
    //  * @param attention:
    //  * @return: java.lang.Integer
    //  **/
    // public Integer insertSubscription(Subscription attention);

    // /**
    //  * Description: 取消关注
    //  * @Author: Bernie_fang
    //  * @Since: 2022/2/8 15:56
    //  * @param subscribe:
    //  * @param beenSubscribed:
    //  * @return: java.lang.Integer
    //  **/
    // public Integer unSubscription(String subscribe, String beenSubscribed);

    /**
     * Description: 查找关注记录
     * @Author: Bernie_fang
     * @Since: 2022/2/8 16:05
     * @param subscribe:
     * @param beenSubscribed:
     * @return: java.lang.Integer
     **/
    public Subscription findSubscription(String subscribe, String beenSubscribed);

    /**
     * Description: 查找关注 ${param} 的人
     * @Author: Bernie_fang
     * @Since: 2022/2/8 16:00
     * @param beenSubscribed:
     * @return: java.lang.Integer
     **/
    public List<Subscription> querySubscribe(String beenSubscribed);

    /**
     * Description: 查找被 ${param} 关注的人
     * @Author: Bernie_fang
     * @Since: 2022/2/8 16:03
     * @param subscribe:
     * @return: java.lang.Integer
     **/
    public List<Subscription> queryBeenSubscribed(String subscribe);



}
