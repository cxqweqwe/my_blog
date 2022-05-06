package com.fang.backgroundapi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.controller.BaseController;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.mapper.ArticleCommentMapper;
import com.fang.backgroundapi.pojo.DO.ReportInfo;
import com.fang.backgroundapi.pojo.DO.SystemSettings;
import com.fang.backgroundapi.admin.service.impl.AdminServiceImpl;
import com.fang.backgroundapi.service.impl.ReportInfoServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersAuthorityServiceImpl;
import com.fang.backgroundapi.service.impl.SystemSettingsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ReportInfoServiceImpl reportInfoService;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Value("${com.fang.blogURL}")
    private String blogURL;

    @Value("${com.fang.forumURL}")
    private String forumURL;

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

    @GetMapping("/setting_cancel/admin")
    @ApiOperation(value = "审判用户", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root"})
    public ServerResponse setting_cancel_admin(@RequestParam("authorId") String authorId, @RequestParam("admin") Integer admin) {
        adminService.settingCancelAdmin(authorId, admin);
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

    // 暂时不做。 论贴举报要改
    @GetMapping("/query/report")
    @ApiOperation(value = "查找信息举报", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse queryReport(@RequestParam("curPage") Integer curPage,
                                      @RequestParam("size") Integer size) {
        Page<ReportInfo> page = new Page<>(curPage, size);
        reportInfoService.page(page);
        List<ReportInfo> reportInfos = page.getRecords();
        reportInfos.stream().forEach(reportInfo -> {
            // 0:博客  1:论贴  2:博客评论  3:论贴评论
            if ("0".equals(reportInfo.getReport())) {
                reportInfo.setReport(blogURL + reportInfo.getReport());
            } else if ("1".equals(reportInfo.getReport())) {
                reportInfo.setReport(forumURL + reportInfo.getReport());
            } else if ("2".equals(reportInfo.getReport())) {


            } else if ("3".equals(reportInfo.getReport())) {
                reportInfo.setReport(blogURL + reportInfo.getReport());
            }
        });

        return ServerResponse.success(new PagingData(page.getTotal(), page.getRecords()));
    }

    @GetMapping("/trial/report")
    @ApiOperation(value = "处理举报信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse findReport(@PathVariable("id") String id) {

        return ServerResponse.success();
    }


    @GetMapping("/query/forum")
    @ApiOperation(value = "查询论贴", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse queryForum(@RequestParam("curPage") Integer curPage, @RequestParam("size") Integer size) {
        PagingData data = adminService.queryForum(curPage, size);
        return ServerResponse.success(data);
    }

    @GetMapping("/trial/forum")
    @ApiOperation(value = "审判论贴", response = ServerResponse.class, httpMethod = "GET")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public ServerResponse trialForum(@RequestParam("postId") String postId, @RequestParam("status") Integer status) {
        adminService.trialForum(postId, status);
        return ServerResponse.success();
    }


}
