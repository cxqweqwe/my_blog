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
 * 图片base64存储表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageBase64 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * base64编码
     */
    private String base64;

    /**
     * 图片名字
     */
    private String imageName;

    /**
     * 文件扩展名(jpg, png)
     */
    private String fileExtName;

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
     * 软删除
     */
    @TableLogic
    private Integer deleted;


}
