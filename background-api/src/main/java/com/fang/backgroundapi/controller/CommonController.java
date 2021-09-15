package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DTO.LoginDTO;
import com.fang.backgroundapi.pojo.DTO.RegisterDTO;
import com.fang.backgroundapi.service.impl.CommonServiceImpl;
import com.fang.backgroundapi.utils.RedisUtils;
import com.fang.backgroundapi.utils.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:38
 **/
@RestController
@RequestMapping("/common")
@Api(tags = "系统公共接口")
@Validated   // Get请求方法需要这个注解配合
public class CommonController {

    @Autowired
    private CommonServiceImpl commonService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse login(@RequestBody @Valid LoginDTO loginDTO,
                                HttpServletRequest request, HttpServletResponse response) throws MyException {
        return commonService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword(), request, response);
    }

    @GetMapping("/login-out")
    @ApiOperation(value = "退出登录接口", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse loginOut(HttpServletRequest request, HttpServletResponse response) throws MyException {
        return commonService.loginOut(request, response);
    }


    @GetMapping("/code/email/{email}")
    @ApiOperation(value = "获取邮箱验证码", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse getEmailCode(@PathVariable("email") @Email(message = "请输入一个合法的邮件地址") String email) throws MyException {
        return commonService.getEmailCode(email);
    }

    @GetMapping("/code/phone/{phone}")
    @ApiOperation(value = "获取手机验证码", response = ServerResponse.class, httpMethod = "GET")
    public ServerResponse getPhoneCode(@PathVariable("phone")
                                       @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "请输入一个合法的手机号码")
                                               String phone) throws Exception {
        return commonService.getPhoneCode(phone);
    }

    /**
     * @Description: 改为由前端生成随机的验证码传到后端。本来应该在前端做图片生成，但是前端不熟悉+后端代码有现成的
     * @Author: Bernie_fang
     * @Since: 2021/9/15 17:58
     * @param codeString:
     * @param request:
     * @param response:
     * @return: void
     **/
    @GetMapping("/code/captcha")
    @ApiOperation(value = "获取图片验证码", response = ServerResponse.class, httpMethod = "GET")
    public void getCaptchaCode(@RequestParam("codeString") String codeString,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");//声明返回类型
        ValidateCode vCode = new ValidateCode();//new一个对象
        // String codeString = vCode.createCode();          // 验证码改为前端传
        HttpSession session = request.getSession();
        session.setAttribute("checkCode", codeString);  //设置session
        OutputStream out = response.getOutputStream();
        ImageIO.write(vCode.getCodeImg(codeString), "jpg", out);// 输出给前端
    }

    /**
     * @Description: 向两个表操作，需要开启事务
     * @Author: Bernie_fang
     * @Since: 2021/9/15 20:55
     * @param registerDTO:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @PostMapping("/registe")
    @ApiOperation(value = "注册接口", response = ServerResponse.class, httpMethod = "POST")
    @Transactional(propagation = Propagation.REQUIRED, value = "detTransactionManager",
            rollbackFor = {Exception.class, RuntimeException.class, MyException.class})
    public ServerResponse registe(@RequestBody @Valid RegisterDTO registerDTO) {
        return commonService.registe(registerDTO);
    }

}
