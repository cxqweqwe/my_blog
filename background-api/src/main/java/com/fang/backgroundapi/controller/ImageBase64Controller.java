package com.fang.backgroundapi.controller;


import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.ImageBase64;
import com.fang.backgroundapi.pojo.DTO.ImageBase64DTO;
import com.fang.backgroundapi.pojo.DTO.LoginDTO;
import com.fang.backgroundapi.service.impl.ImageBase64ServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 图片base64存储表 前端控制器
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/imageBase64")
@Api(tags = "图片base64相关方法接口")
@Validated   // Get请求方法需要这个注解配合
public class ImageBase64Controller {

    @Autowired
    private ImageBase64ServiceImpl imageBase64Service;

    @PostMapping("/upload/image-base64")
    @ApiOperation(value = "上传图片Base64的数据",response = ServerResponse.class,httpMethod = "POST")
    public ServerResponse uploadImageBase64(@RequestBody @Valid ImageBase64DTO imageBase64DTO) {
        return imageBase64Service.uploadImageBase64(imageBase64DTO);
    }

    // @GetMapping("/download/image-base64/{id}")
    // @ApiOperation(value = "获取图片Base64的数据",response = ServerResponse.class,httpMethod = "GET")
    // public ServerResponse downloadImageBase64(@PathVariable("id") String id) {
    //     ImageBase64 base64 = imageBase64Service.findImageBase64ByID(id);
    //     return ServerResponse.success(base64);
    // }

    @GetMapping("/download/image-base64/{id}")
    @ApiOperation(value = "获取图片Base64的数据",response = ServerResponse.class,httpMethod = "GET")
    public String downloadImageBase64(@PathVariable("id") String id) {
        ImageBase64 base64 = imageBase64Service.findImageBase64ByID(id);
        return base64.getBase64();
    }


}

