package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DTO.LoginDTO;
import com.fang.backgroundapi.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:38
 **/
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonServiceImpl commonService;

    @PostMapping("login")
    public ServerResponse login(@RequestBody LoginDTO loginDTO,
                                HttpServletRequest request, HttpServletResponse response) throws MyException {
        return commonService.loginByUsername(loginDTO.getUsername(), loginDTO.getPassword(), request,response);
    }

}
