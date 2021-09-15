package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DTO.SystemUserDTO;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@Api(tags = "系统用户相关接口")
@Validated   // Get请求方法需要这个注解配合
public class SysUsersController {

    @Autowired
    private SysUsersServiceImpl sysUsersService;


}

