package com.fang.backgroundapi.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/1/30 23:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloggerVO {

    private String authorId;
    private String nickName;
    private String avatarPath;
    private String beenViews;
    private String original;   //原创
    private String subscribed; //被订阅
    private String settled;    //入驻时间
    private String personalProfile;//个人简介
    private String school;
    private String companyName;

}
