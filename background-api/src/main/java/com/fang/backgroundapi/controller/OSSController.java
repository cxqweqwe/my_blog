package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.ImageUpload;
import com.fang.backgroundapi.service.impl.ImageUploadServiceImpl;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
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

import java.io.IOException;

/**
 * @author Bernie_fang
 * @Description: OSS对象存储控制器
 * @create 2022/1/16 14:36
 **/
@RestController
@RequestMapping("/oss")
@Api(tags = "OSS对象存储控制器")
@Slf4j
public class OSSController extends BaseController {

    @Value("${com.fang.uploadSize}")
    private String uploadSize;

    @Autowired
    private ImageUploadServiceImpl imageUploadService;


    private final String ACCESS_KEY = "ahJvPH-dwkB5v3kLnexV08lvUeIjtZ4GiQtOBq8d";
    private final String SECRET_KEY = "JIXqFaujPeqg1ska3wKtgjdWFoNpSi2pPix3xVj9";
    private final String BUCKET = "fangweb";


    @PostMapping("/upload")
    @ApiOperation(value = "上传接口", response = ServerResponse.class, httpMethod = "POST")
    public ServerResponse upload(MultipartFile file, String articleOrPostId) throws MyException {
        if (StringUtils.isEmpty(articleOrPostId)) {
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

        // 自检通过，上传到七牛云,这些都是默认配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        Region region = new Region.Builder().
                region("z2").
                srcUpHost("up-z2.qiniup.com", "up-dg.qiniup.com", "up-fs.qiniup.com").
                accUpHost("upload-z2.qiniup.com", "upload-dg.qiniup.com", "upload-fs.qiniup.com").
                iovipHost("iovip-z2.qbox.me").
                rsHost("rs-z2.qbox.me").
                rsfHost("rsf-z2.qbox.me").
                apiHost("api-z2.qiniu.com").
                build();
        Configuration cfg = new Configuration(region);

        cfg.useHttpsDomains = false;
        UploadManager uploadManager = new UploadManager(cfg);
        DefaultPutRet putRet = null;
        try {
            Response response = uploadManager.put(file.getInputStream(),null,upToken,null, null);
            //解析上传成功的结果
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            log.error("OSS存储错误-----> {}", ex.getMessage());
            throw new MyException("出错了", 5000, ex);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("OSS存储错误-----> {}", e.getMessage());
            throw new MyException("出错了", 5000, e);
        }

        if (putRet != null){
            // 数据库保存
            ImageUpload imageUpload = new ImageUpload();
            imageUpload.setArticleOrPostId(articleOrPostId);
            imageUpload.setOriginalName(file.getOriginalFilename());
            imageUpload.setSize(imageSize);
            imageUpload.setAddress("http://image.fangweb.top/" + putRet.key);
            imageUpload.setUploader(authorId);
            imageUpload.setType(fileType[1]);
            imageUploadService.save(imageUpload);
            return ServerResponse.success();
        }
        return ServerResponse.error(5000,"oss存储失败", "NullPointerException");
    }

}
