package com.fang.backgroundapi.pojo.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/12 15:01
 **/
@Data
public class AdminArticle implements Serializable {

    private String articleId;
    private String title;
    private String nickName;
    private Integer deleted;

}
