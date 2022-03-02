package com.fang.backgroundapi.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.BlogInfoDay;
import com.fang.backgroundapi.service.impl.ScheduledServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 博客信息统计，每天 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/blogInfoDay")
@Api(tags = "数据整理")
public class BlogInfoDayController {

    @Autowired
    private ScheduledServiceImpl scheduledService;

    @GetMapping("/get")
    @ApiOperation(value = "获取statistics数据", response = ServerResponse.class, httpMethod = "GET")
    public String loginOut()  {
        ArrayList<List<Object>> dataSet = scheduledService.getData("598108905656729600");
        String jsonStr = JSONUtil.toJsonStr(dataSet);
        return jsonStr;
    }

    @GetMapping("/sendStatistics")
    @ApiOperation(value = "测试发送数据", response = ServerResponse.class, httpMethod = "GET")
    public String sendStatistics()  {
        scheduledService.statistics();
        return "success";
    }

}

