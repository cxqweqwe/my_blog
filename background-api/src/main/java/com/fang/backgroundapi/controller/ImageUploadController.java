package com.fang.backgroundapi.controller;

import cn.hutool.core.util.IdUtil;
import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.ImageUpload;
import com.fang.backgroundapi.service.impl.ImageUploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/1/2 15:40
 **/
@RestController
@RequestMapping("/image")
@Api(tags = "图片接口")
@Slf4j
public class ImageUploadController extends BaseController {

    @Autowired
    private ImageUploadServiceImpl imageUploadService;

    @Value("${com.fang.uploadAddress}")
    private String uploadAddress;

    @Value("${com.fang.uploadSize}")
    private String uploadSize;

    @Value("${com.fang.imageAddress}")
    private String imageAddress;


    @PostMapping("/upload")
    @ApiOperation(value = "上传接口", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse upload(MultipartFile file,String articleOrPostId) throws MyException {
        if (StringUtils.isEmpty(articleOrPostId)){
            log.info("文章ID为空");
            return ServerResponse.error(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getDesc(), null);
        }
        if (file.isEmpty()) {
            return ServerResponse.error(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getDesc(), null);
        }
        String authorId = super.getAuthorId();
        //检查格式
        String[] fileType = file.getContentType().split("/");
        log.info("{}---->上传", authorId);
        log.info("上传文件类型：{}", fileType);
        if (!("png".equals(fileType[1]) || "jpeg".equals(fileType[1]))) {
            log.info("不支持上传{}类型", fileType);
            return ServerResponse.error(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getDesc(), null);
        }
        //检查文件大小
        long imageSize = file.getSize();
        if (imageSize > Long.valueOf(uploadSize)) {
            return ServerResponse.error(ResponseCode.FAIL.getCode(), "图片太大", null);
        }

        String uuid = IdUtil.simpleUUID();
        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setId(uuid);
        imageUpload.setArticleOrPostId(articleOrPostId);
        imageUpload.setOriginalName(file.getOriginalFilename());
        imageUpload.setSize(imageSize);
        imageUpload.setAddress(imageAddress + uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        imageUpload.setUploader(authorId);
        imageUpload.setType(fileType[1]);
        imageUploadService.save(imageUpload);

        // log.info("图片URL:{}" + path);
        File dest = new File(new File(uploadAddress).getAbsolutePath() + "/" +
                uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        try {
            file.transferTo(dest);
            return ServerResponse.success(imageUpload.getAddress());
        } catch (IOException e) {
            log.error("保存图片报错，{}", e.getMessage());
            return ServerResponse.success();
        }

    }

}
