package com.fang.backgroundapi.pojo.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收藏记录表
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FavoriteRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String authorId;

    /**
     * 收藏夹id
     */
    private String favoritesId;

    /**
     * 收藏的博客id
     */
    private String articleId;


}
