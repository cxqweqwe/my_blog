package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.mapper.SysUsersMapper;
import com.fang.backgroundapi.service.SysUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    public SysUsers findUserByUsername(String username) {
        QueryWrapper<SysUsers> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUsers sysUsers = sysUsersMapper.selectOne(wrapper);
        return sysUsers;
    }

    @Override
    public Integer insertUser(SysUsers sysUsers) {
        return sysUsersMapper.insert(sysUsers);
    }

}
