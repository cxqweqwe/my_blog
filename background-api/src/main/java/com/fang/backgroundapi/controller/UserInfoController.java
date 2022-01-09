package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.pojo.DTO.UserInfoDTO;
import com.fang.backgroundapi.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "系统用户信息相关接口")
@Validated   // Get请求方法需要这个注解配合
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;


    /**
     * @Description: 接口信息排掉了邮箱和手机号码. 此接口不需要，为空接口
     * @Author: Bernie_fang
     * @Since: 2021/9/13 16:24
     * @param userInfoDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/addUserInfo")
    @ApiOperation(value = "添加用户信息", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse addUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO) {
        return userInfoService.addUserInfo(userInfoDTO);
    }

    /**
     * @Description: 接口信息排掉了邮箱和手机号码.
     * @Author: Bernie_fang
     * @Since: 2021/9/13 21:13
     * @param userInfoDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/updateUserInfo")
    @ApiOperation(value = "修改/填写 用户信息", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updateUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO) {
        return userInfoService.updateUserInfo(userInfoDTO);
    }

    @GetMapping("/inquire/{authorId}")
    @ApiOperation(value = "查询一个用户信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse findUserInfoByAuthorId(@PathVariable("authorId") @NotBlank(message = "参数不能为空") @ApiParam("作者ID") String authorId) {
        UserInfo info = userInfoService.findUserInfoByAuthorId(authorId);
        if (info == null){
            return ServerResponse.notData();
        }
        return ServerResponse.success(info);
    }

    @GetMapping("/inquire/all")
    @ApiOperation(value = "查询全部用户信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryUserInfo(@RequestParam @ApiParam("参数必须为数字") Integer currentPage,
                                        @RequestParam @ApiParam("参数必须为数字") Integer size) {
        return userInfoService.queryUserInfo(currentPage,size);
    }



}

