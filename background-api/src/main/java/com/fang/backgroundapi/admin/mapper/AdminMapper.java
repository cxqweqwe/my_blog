package com.fang.backgroundapi.admin.mapper;

import com.fang.backgroundapi.pojo.VO.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    public Integer changeUser(@Param("authorId")String authorId, @Param("status") Integer status);

}
