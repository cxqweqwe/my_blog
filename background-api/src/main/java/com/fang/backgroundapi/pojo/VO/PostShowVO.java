package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Bernie_fang
 * @Description: 博客基本信息展示类
 * @create 2021/12/25 16:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostShowVO {

    private String articleId;
    private String authorId;
    private String title;
    private String avatarPath;
    private String nickName;
    private String briefIntroduction;//简介
    private Integer pageviews;
    private Integer likes;
    private Integer collection;
    @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
    private Date createTime;
    @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
    private Date modifiedTime;

}
