package com.fang.backgroundapi.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class FavoritesBar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "favorites_id", type = IdType.ASSIGN_UUID)
    private String favoritesId;

    /**
     * 用户ID
     */
    private String authorId;

    /**
     * 收藏夹名字
     */
    private String favoritesName;

    /**
     * 收藏夹简介
     */
    private String favoritesIntro;

    /**
     * 收藏数量(暂时限定上限1000条)
     */
    private Integer favoritesNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 软删除
     */
    @TableLogic
    private Integer deleted;


}
