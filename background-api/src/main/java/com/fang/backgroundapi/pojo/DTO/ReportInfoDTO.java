package com.fang.backgroundapi.pojo.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class ReportInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
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


}
