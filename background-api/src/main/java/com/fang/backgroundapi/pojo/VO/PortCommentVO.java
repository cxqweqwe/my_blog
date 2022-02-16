package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/16 15:15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortCommentVO implements Serializable {

    /**
     * 评论ID
     */
    private String id;

    /**
     * 评论者ID
     */
    private String authorId;

    /**
     * 评论者ID
     */
    private String avatarPath;

    private String nickName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 图片路径
     */
    private String imagePath;

    @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
    private Date createTime;


}
