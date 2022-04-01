package com.fang.backgroundapi.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.Subscription;
import com.fang.backgroundapi.service.impl.SubscriptionServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 订阅记录表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-08
 */
@RestController
@RequestMapping("/subscription")
public class SubscriptionController extends BaseController {

    @Autowired
    private SubscriptionServiceImpl subscriptionService;

    @GetMapping("/subscribe/{beenSubscribed}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "添加订阅", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse addSubscription(@PathVariable("beenSubscribed") @NotBlank(message = "参数能为空")String beenSubscribed) throws MyException {
        String subscribe = super.getAuthorId();
        if (subscribe.equals(beenSubscribed)){
            return ServerResponse.success(400, "你就是最棒的，不需要订阅自己", null);
        }
        Subscription subscription = subscriptionService.findSubscription(subscribe, beenSubscribed);
        if (ObjectUtil.isNotEmpty(subscription)){
            // 不为空，已经关注了
            return ServerResponse.success(400, "您已关注该作者，无需重复关注", null);
        }
        subscription = new Subscription();
        subscription.setSubscribe(subscribe);
        subscription.setBeenSubscribed(beenSubscribed);
        subscriptionService.save(subscription);
        return ServerResponse.success();
    }

    /**
     * Description:
     * @Author: Bernie_fang
     * @Since: 2022/2/8 16:18
     * @param beenSubscribed:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @GetMapping("/unSubscribe/{beenSubscribed}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "取消订阅", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse unSubscription(@PathVariable("beenSubscribed") @NotBlank(message = "参数不能为空") String beenSubscribed) throws MyException {
        UpdateWrapper<Subscription> wrapper = new UpdateWrapper<>();
        wrapper.set("deleted","1")
                .eq("subscribe", super.getAuthorId())
                .eq("been_subscribed", beenSubscribed);
        subscriptionService.update(wrapper);
        return ServerResponse.success();
    }

    @GetMapping("/check/{beenSubscribed}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "查询是否订阅", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse checkSubscription(@PathVariable("beenSubscribed") @NotBlank(message = "参数不能为空") String beenSubscribed) throws MyException {
        String subscribe = super.getAuthorId();
        Subscription subscription = subscriptionService.findSubscription(subscribe, beenSubscribed);
        if (ObjectUtil.isNotEmpty(subscription)){
            // 不为空，已经关注了
            return ServerResponse.success(true);
        }
        return ServerResponse.success(false);
    }

}

