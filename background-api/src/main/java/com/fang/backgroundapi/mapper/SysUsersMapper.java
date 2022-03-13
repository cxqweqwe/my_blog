package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.typehandler.Encrypt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface SysUsersMapper extends BaseMapper<SysUsers> {

    public Encrypt findEmailByUsername(@Param("username") String username);

    public Integer updatePassword(@Param("username")String username,
                                  @Param("password")String password,
                                  @Param("modifiedTime")Date modifiedTime);

}
