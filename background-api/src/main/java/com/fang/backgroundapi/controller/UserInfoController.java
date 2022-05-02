package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.pojo.DTO.UserInfoDTO;
import com.fang.backgroundapi.pojo.VO.UserInfoVO;
import com.fang.backgroundapi.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
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
import javax.validation.constraints.Email;
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
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoServiceImpl userInfoService;


    // /**
    //  * @Description: 接口信息排掉了邮箱和手机号码. 此接口不需要，为空接口
    //  * @Author: Bernie_fang
    //  * @Since: 2021/9/13 16:24
    //  * @param userInfoDTO:
    //  * @return: com.fang.backgroundapi.common.ServerResponse
    //  **/
    // @PostMapping("/addUserInfo")
    // @ApiOperation(value = "添加用户信息", response = ServerResponse.class, httpMethod = "POST")
    // public ServerResponse addUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO) {
    //     return userInfoService.addUserInfo(userInfoDTO);
    // }

    /**
     * @Description: 接口信息排掉了邮箱和手机号码.
     * @Author: Bernie_fang
     * @Since: 2021/9/13 21:13
     * @param userInfoDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/updateUserInfo")
    // @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "修改/填写 用户信息", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updateUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        return userInfoService.updateUserInfo(userInfoDTO);
    }

    /**
     * Description: 修改手机号码
     * @param phoneNumber:
     * @Author: Bernie_fang
     * @Since: 2022/1/9 17:11
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/update/phone")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "修改手机号码", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updatePhone(@NotBlank(message = "参数不能为空")  @RequestParam String phoneNumber,@NotBlank(message = "参数不能为空") @RequestParam String code) throws MyException {
        String authorId = super.getAuthorId();
        return userInfoService.updatePhone(phoneNumber,code,authorId);
    }

    /**
     * Description: 修改邮箱
     * @param email:
     * @Author: Bernie_fang
     * @Since: 2022/1/9 17:11
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/update/email")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "修改邮箱", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updateEmail(@Email(message = "email不合法") @RequestParam String email,@NotBlank(message = "参数不能为空") @RequestParam String code) throws MyException {
        String authorId = super.getAuthorId();
        return userInfoService.updateEmail(email,code,authorId);
    }

    /**
     * Description: 修改头像
     * @param avatar: 图片的base64编码
     * @Author: Bernie_fang
     * @Since: 2022/1/9 17:11
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/update/avatar")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "修改手机号码", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse updateAvatar(@NotBlank(message = "email不合法") String avatar) throws MyException {
        String authorId = super.getAuthorId();
        return userInfoService.updateAvatar(avatar, authorId);
    }

    @GetMapping("/inquire/{authorId}")
    // @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "查询一个用户信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse findUserInfoByAuthorId(@PathVariable("authorId") @NotBlank(message = "参数不能为空") @ApiParam("作者ID") String authorId) {
        UserInfo info = userInfoService.findUserInfoByAuthorId(authorId);
        if (info == null){
            return ServerResponse.notData();
        }
        UserInfoVO infoVO = new UserInfoVO();
        BeanUtils.copyProperties(info, infoVO);
        infoVO.setPhoneNumber(info.getPhoneNumber()==null?"":info.getPhoneNumber().getValue());
        infoVO.setEmail(info.getEmail().getValue());
        return ServerResponse.success(infoVO);
    }

    @GetMapping("/inquire/all")
    @RequiresRoles(value = {"root", "admin", "userVip"}, logical = Logical.OR)
    @ApiOperation(value = "查询全部用户信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse queryUserInfo(@RequestParam @ApiParam("参数必须为数字") Integer currentPage,
                                        @RequestParam @ApiParam("参数必须为数字") Integer size) {
        return userInfoService.queryUserInfo(currentPage,size);
    }

    @GetMapping("/blogger/{authorId}")
    @ApiOperation(value = "查询全部用户信息", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse getBlogger(@PathVariable("authorId") String authorId) {
         return userInfoService.getBlogger(authorId);
    }





}

