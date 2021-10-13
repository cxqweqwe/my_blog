package com.fang.backgroundapi.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/10/13 15:29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlogVO {

    private String articleId;
    private String authorId;
    private String title;
    private String avatarPath;
    private String nickName;
    private String briefIntroduction;//简介
    private Integer pageviews;
    private Integer likes;
    private Integer collection;

}
