package com.fang.backgroundapi.admin.mapper;

import com.fang.backgroundapi.pojo.VO.AdminArticle;
import com.fang.backgroundapi.pojo.VO.AdminComment;
import com.fang.backgroundapi.pojo.VO.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/10 21:53
 **/
@Repository
@Mapper
public interface AdminMapper {

    public List<AdminUser> queryUser(@Param("curPage") Integer curPage, @Param("size") Integer size);

    public Integer changeUser(@Param("authorId")String authorId, @Param("status") Integer status, @Param("modifiedTime") Date modifiedTime);

    public void settingCancelAdmin(@Param("authorId")String authorId, @Param("admin")Integer admin,  @Param("modifiedTime")Date modifiedTime);

    public List<AdminArticle> queryArticle(@Param("curPage") Integer curPage, @Param("size") Integer size);

    public Long countArticle();

    public Integer trialArticle(@Param("articleId")String articleId, @Param("status") Integer status, @Param("modifiedTime") Date modifiedTime);

    public List<AdminComment> queryComment(@Param("blogURL") String blogURL, @Param("forumURL") String forumURL,
            @Param("curPage") Integer curPage, @Param("size") Integer size);

    public List<Long> countComment();

    public Integer trialArticleComment(@Param("id")String id, @Param("status") Integer status, @Param("modifiedTime") Date modifiedTime);

    public Integer trialPortComment(@Param("id")String id, @Param("status") Integer status, @Param("modifiedTime") Date modifiedTime);

}
