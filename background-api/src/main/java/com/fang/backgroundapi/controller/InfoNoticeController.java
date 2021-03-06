package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.InfoNotice;
import com.fang.backgroundapi.service.impl.InfoNoticeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 信息通知记录表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/infoNotice")
public class InfoNoticeController {

    @Autowired
    private InfoNoticeServiceImpl infoNoticeService;

    @GetMapping("/query/{authorId}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "获取全部", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryNotice(@PathVariable("authorId") String authorId) {
        List<InfoNotice> infoNoticeList = infoNoticeService.readAndQueryInfoNotice(authorId);
        return ServerResponse.success(infoNoticeList);
    }

    @GetMapping("/unRead/{authorId}")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "获取全部", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse unReadCount(@PathVariable("authorId") String authorId) {
         Long count = infoNoticeService.queryUnreadCount(authorId);
         return ServerResponse.success(count);
    }



}

