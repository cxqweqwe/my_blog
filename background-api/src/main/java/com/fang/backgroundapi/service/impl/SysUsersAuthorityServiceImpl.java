package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.SysUsersAuthority;
import com.fang.backgroundapi.mapper.SysUsersAuthorityMapper;
import com.fang.backgroundapi.service.SysUsersAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class SysUsersAuthorityServiceImpl extends ServiceImpl<SysUsersAuthorityMapper, SysUsersAuthority> implements SysUsersAuthorityService {

    @Autowired
    private SysUsersAuthorityMapper authorityMapper;

    /**
     * @Description: 根据用户的作者id查询权限
     * @Author: Bernie_fang
     * @Since: 2021/8/18 22:56
     * @param authorId:
     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.SysUsersAuthority>
     **/
    @Override
    public List<String> querySysUsersAuthorityByAuthorId(String authorId) {
        List<String> authorities = authorityMapper.querySysUsersAuthorityByAuthorId(authorId);
        return authorities;
    }

    @Override
    public Integer addSysUsersAuthorityByAuthorId(String authorityRole, String authorId) {
        SysUsersAuthority authority = new SysUsersAuthority();
        authority.setAuthorityRole(authorityRole);
        authority.setAuthorId(authorId);
        return authorityMapper.insert(authority);
    }

}
