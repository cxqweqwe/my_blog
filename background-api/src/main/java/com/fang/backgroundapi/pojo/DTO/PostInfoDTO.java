package com.fang.backgroundapi.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/2/12 15:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PostInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    private String postId;

    /**
     * 创建者ID
     */
    private String authorId;

    /**
     * 帖子名字
     */
    private String postName;

    /**
     * 借鉴帖子内容
     */
    private String postDescription;

    /**
     * 帖子有关图片
     */
    private String imagePath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "dd MMMM,yyyy",timezone = "US")
    private Date createTime;


}
