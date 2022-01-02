package com.fang.backgroundapi.pojo.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bernie_fang
 * @Description: 图片上传信息类
 * @create 2021/12/29 22:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUpload extends Base {

    private static final long serialVersionUID = 1L;

    /**
     * 上传人,作者id
     */
    private String uploader;
    /**
     * 博客或者论坛id
     */
    private String articleOrPostId;
    /**
     * 原名字
     */
    private String originalName;
    /**
     * 图片地址
     */
    private String address;
    /**
     * 图片类型，png/jpg
     */
    private String type;
    /**
     * 图片大小,最大500k
     */
    private Long size;



}
