package com.fang.backgroundapi.pojo.DTO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收藏夹信息表
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FavoritesBarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String favoritesId;

    /**
     * 用户ID
     */
    private String authorId;

    /**
     * 收藏夹名字
     */
    @NotBlank(message = "名字不能为空")
    private String favoritesName;

    /**
     * 收藏夹简介
     */
    private String favoritesIntro;

}
