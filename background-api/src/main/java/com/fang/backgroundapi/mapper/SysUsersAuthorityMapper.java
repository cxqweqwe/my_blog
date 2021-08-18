package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.SysUsersAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户权限表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface SysUsersAuthorityMapper extends BaseMapper<SysUsersAuthority> {

    /**
     * @Description: 根据用户的作者id查询权限
     * @Author: Bernie_fang
     * @Since: 2021/8/18 23:02
     * @param authorId:
     * @return: java.util.List<java.lang.String>
     **/
    public List<String> querySysUsersAuthorityByAuthorId(@Param("authorId") String authorId);

}
