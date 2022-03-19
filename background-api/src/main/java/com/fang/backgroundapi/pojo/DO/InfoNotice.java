package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 信息通知记录表
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("info_notice")
public class InfoNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 被通知人
     */
    private String beNotified;

    /**
     * 新发布的博客id
     */
    private String articleId;

    /**
     * 博文作者Id
     */
    private String authorId;

    /**
     * 作者昵称
     */
    private String nickName;
    /**
     * 博文标题
     */
    private String title;
    /**
     * 是否已读，0:未读，1:已读
     */
    private Integer isRead;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;


}
