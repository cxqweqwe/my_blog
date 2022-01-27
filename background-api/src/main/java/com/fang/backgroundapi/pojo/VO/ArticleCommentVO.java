package com.fang.backgroundapi.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bernie_fang
 * @Description: 评论展示类，添加评论也用这个类接收
 * @create 2022/1/26 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticleCommentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    private String id;

    /**
     * 博客ID,标注在哪条评论下面
     */
    private String articleId;

    /**
     * 评论者ID
     */
    private String authorId;

    /**
     * 评论者nickName
     */
    private String nickName;
    /**
     * 评论人头像路径
     */
    private String avatarPath;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 评论内容
     */
    private String comment;// 对的

    /**
     * 评论内容html
     */
    private String commentContentHtml;

    /**
     * 评论回复，填写评论的ID，被评论数据id
     */
    private String beenCommentedId;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    /**
     * 第一条评论id
     */
    private String firstComment;

    private String beenCommentedAuthorId;// 被评论人id
    private String beenCommentedNickName;// 被评论人昵称
    private String beenCommentedAvatarPath;// 被评论人头像路径

    private Boolean inputShow = false;//前端需要的参数，忽略即可

    private List<ArticleCommentVO> replyList;

}
