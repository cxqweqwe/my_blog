package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 举报信息表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReportInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 举报对象【博客，帖子，评论】
     */
    private String report;

    /**
     * 举报类型【0:博客 1:论贴 2:博客评论 3:论贴评论】
     */
    private String reportType;

    /**
     * 举报人
     */
    private String authorId;

    /**
     * 违规原因
     */
    private String violationReason;

    /**
     * 违规类型【涉黄，政治不正确等原因】
     */
    private String violationType;

    /**
     * 举报人联系方式【手机微信邮箱等】
     */
    private String contactDetails;

    /**
     * 处理情况【0：未处理，1：已处理】
     */
    private Integer handlingSituation;

    /**
     * 处理方式【暂时设置为自定义】
     */
    private String handlingType;

    /**
     * 处理时间
     */
    private Date handlingTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;

    /**
     * 软删除
     */
    @TableLogic
    private Integer deleted;


}
