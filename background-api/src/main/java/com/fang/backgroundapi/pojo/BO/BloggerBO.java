package com.fang.backgroundapi.pojo.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/1/30 23:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloggerBO {

    private String authorId;
    private String avatarPath;
    private String nickName;
    private String beenViews;
    private String create;
    private String subscribed; //被订阅
    private String settled;
    private String personalProfile;//个人简介
    private String school;
    private String companyName;


}
