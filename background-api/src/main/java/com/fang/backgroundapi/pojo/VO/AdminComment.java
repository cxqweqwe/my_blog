package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/12 16:11
 **/
@Data
public class AdminComment implements Serializable {

    private String id;
    private String type;//博客or论贴
    private String commentObject; //评论对象
    private String content; //评论内容
    private String authorId;
    private String nickName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer deleted;


}
