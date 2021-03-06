package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.SysUsersAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户权限表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface SysUsersAuthorityService extends IService<SysUsersAuthority> {

    public List<String> querySysUsersAuthorityByAuthorId(String authorId);

    public Integer addSysUsersAuthorityByAuthorId(String authorityRole, String authorId);

}
