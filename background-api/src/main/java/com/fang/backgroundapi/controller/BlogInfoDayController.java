package com.fang.backgroundapi.controller;


import cn.hutool.core.date.DateUtil;
import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.service.impl.AsyncService;
import com.fang.backgroundapi.service.impl.ScheduledServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import com.fang.backgroundapi.service.impl.UserInfoServiceImpl;
import com.fang.backgroundapi.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 博客信息统计，每天 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/blogInfoDay")
@Api(tags = "数据整理")
public class BlogInfoDayController extends BaseController {

    @Autowired
    private ScheduledServiceImpl scheduledService;

    /**
     * Description: 测试获取数据，测试完成即可关闭接口
     *
     * @Author: Bernie_fang
     * @Since: 2022/3/3 22:22
     * @return: java.lang.String
     **/
    // @GetMapping("/get")
    // @ApiOperation(value = "获取statistics数据", response = ServerResponse.class, httpMethod = "GET")
    // @ResponseBody
    // public String get() {
    //     ArrayList<List<Object>> dataSet = scheduledService.getData("598108905656729600");
    //     String jsonStr = JSONUtil.toJsonStr(dataSet);
    //     return jsonStr;
    // }

    // @GetMapping("/sendStatistics")
    // @ApiOperation(value = "测试发送数据", response = ServerResponse.class, httpMethod = "GET")
    // public String sendStatistics(@RequestParam("authorId") String authorId, Model model) {
    //     SysUsers user = usersService.findUserById(authorId);
    //     String token = JWTUtil.signToToken(user.getUsername(), user.getAuthorId(), CommonInfo.THREE_DAY_EXPIRATION_TIME_MILLISECOND);
    //     String href = "http://localhost:8081/blogInfoDay/show/" + token + "/2022-02-28";
    //
    //     asyncService.sendEmailStatistics(href, "448970028@qq.com");
    //
    //     return "statisticsRemind";
    // }

    @GetMapping("/sendEmail")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "测试发送邮件", response = ServerResponse.class, httpMethod = "GET")
    @ResponseBody
    public String sendEmail() {
        scheduledService.statistics();
        return "success";
    }

}

