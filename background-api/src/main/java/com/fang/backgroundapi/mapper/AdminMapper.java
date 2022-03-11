package com.fang.backgroundapi.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.VO.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/10 21:53
 **/
@Repository
public interface AdminMapper {

    public List<AdminUser> queryUser(@Param("curPage") Integer curPage, @Param("size") Integer size);

    public Integer changeUser(@Param("authorId")String authorId, @Param("status") Integer status);

}
