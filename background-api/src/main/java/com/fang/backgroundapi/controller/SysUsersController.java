package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/sysUsers")
public class SysUsersController {

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @GetMapping("/test")
    public String test(){
        return "success";
    }


}

