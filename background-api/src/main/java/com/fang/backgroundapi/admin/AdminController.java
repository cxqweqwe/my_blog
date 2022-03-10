package com.fang.backgroundapi.admin;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.controller.BaseController;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.DTO.LoginDTO;
import com.fang.backgroundapi.service.impl.AdminServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersAuthorityServiceImpl;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class AdminController extends BaseController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private SysUsersAuthorityServiceImpl sysUsersAuthorityService;

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


}
