package com.fang.backgroundapi.pojo.DO;

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
 * 论坛的帖子信息
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 帖子ID
     */
    @TableId(value = "post_id", type = IdType.ASSIGN_UUID)
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
    private String iamgePath;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    /**
     * 0表示正常(可用)，1表示删除(不可用)
     */
    @TableLogic
    private Integer deleted;

    /**
     * 状态【0：违规，1：正常，2：等待审核】
     */
    private Integer state;


}
