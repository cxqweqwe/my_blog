package com.fang.backgroundapi.controller;

import cn.hutool.core.date.DateUtil;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.service.impl.ScheduledServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import com.fang.backgroundapi.service.impl.UserInfoServiceImpl;
import com.fang.backgroundapi.utils.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/3 23:07
 **/
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private ScheduledServiceImpl scheduledService;

    @GetMapping("/show/{token}/{date}")
    // @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "获取statistics数据", response = ServerResponse.class, httpMethod = "GET")
    public String show(@PathVariable("token") String token, @PathVariable("date") String strDate,
                       Model model) {
        String authorId = JWTUtil.getAuthorId(token);

        Date date = DateUtil.parse(strDate);
        ArrayList<List<Object>> dataSet = scheduledService.getData(authorId, date);
        UserInfo info = userInfoService.findUserInfoByAuthorId(authorId);
        model.addAttribute("dataSet", dataSet);
        model.addAttribute("title", info.getNickName() + " - 每周数据统计");
        model.addAttribute("date", strDate);
        return "statisticsTemplate";
    }

}
