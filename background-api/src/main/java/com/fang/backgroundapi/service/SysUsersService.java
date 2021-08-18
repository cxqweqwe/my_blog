package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface SysUsersService {

    public SysUsers findUserByUsername(String username);

    public Integer insertUser(SysUsers sysUsers);

    // public Integer updatePassword(String password);

    // public SysUsers queryUser();

}
