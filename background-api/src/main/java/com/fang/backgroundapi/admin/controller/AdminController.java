package com.fang.backgroundapi.admin.controller;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.controller.BaseController;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.SystemSettings;
import com.fang.backgroundapi.admin.service.impl.AdminServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersAuthorityServiceImpl;
import com.fang.backgroundapi.service.impl.SystemSettingsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/9 22:40
 **/
@RestController
@RequestMapping("/sys/admin")
@Api(tags = "管理员权限接口")
public class AdminController extends BaseController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private SysUsersAuthorityServiceImpl sysUsersAuthorityService;

    @Autowired
    private SystemSettingsServiceImpl settingsService;

    @GetMapping("/check/user")
    @ApiOperation(value = "查找全部用户", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse check() throws MyException {
        String authorId = super.getAuthorId();
        List<String> data = sysUsersAuthorityService.querySysUsersAuthorityByAuthorId(authorId);
        return ServerResponse.success(data);
    }

    @GetMapping("/query/user")
    @ApiOperation(value = "查找全部用户", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse queryUser(@RequestParam("curPage") Integer curPage, @RequestParam("size") Integer size) {
        PagingData data = adminService.queryUser(curPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/trial/user")
    @ApiOperation(value = "审判用户", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse trialUser(@RequestParam("authorId") String authorId, @RequestParam("status") Integer status) {
        adminService.changeUser(authorId, status);
        return ServerResponse.success();
    }

    @GetMapping("/change/setting")
    @ApiOperation(value = "更改系统设置", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse changeSetting(@RequestParam("settingId") String settingId, @RequestParam("isAllow") Integer isAllow) {
        SystemSettings systemSettings = new SystemSettings();
        systemSettings.setId(settingId);
        systemSettings.setAllowOrNot(isAllow);
        settingsService.updateSystemSetting(systemSettings);
        return ServerResponse.success();
    }

    @GetMapping("/query/setting")
    @ApiOperation(value = "查询系统设置", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse querySetting() {
        List<SystemSettings> systemSettings = settingsService.querySystemSetting();
        return ServerResponse.success(systemSettings);
    }

    @GetMapping("/query/article")
    @ApiOperation(value = "查询文章", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse queryArticle(@RequestParam("curPage") Integer curPage, @RequestParam("size") Integer size) {
        PagingData data = adminService.queryArticle(curPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/trial/article")
    @ApiOperation(value = "审判文章", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse trialArticle(@RequestParam("articleId") String articleId, @RequestParam("status") Integer status) {
        adminService.trialArticle(articleId, status);
        return ServerResponse.success();
    }

    @GetMapping("/query/comment")
    @ApiOperation(value = "查询评论", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse queryComment(@RequestParam("curPage") Integer curPage, @RequestParam("size") Integer size) {
        PagingData data = adminService.queryComment(curPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/trial/comment")
    @ApiOperation(value = "审判评论", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse trialComment(@RequestParam("type") Integer type, @RequestParam("id") String id, @RequestParam("status") Integer status) {
        adminService.trialComment(type, id, status);
        return ServerResponse.success();
    }

}
