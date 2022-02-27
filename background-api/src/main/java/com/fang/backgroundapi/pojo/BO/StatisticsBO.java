package com.fang.backgroundapi.pojo.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/27 17:23
 **/
@Data
public class StatisticsBO implements Serializable {

    private Integer pageviews = 0;              // 浏览量
    private Integer likes = 0;                  // 点赞量
    private Integer subscription = 0;           // 订阅量
    private Integer comment = 0;                // 评论量
    private Integer collection = 0;             // 收藏量

}
