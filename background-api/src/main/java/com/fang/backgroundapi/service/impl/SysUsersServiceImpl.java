package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.mapper.SysUsersMapper;
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.pojo.DTO.SystemUserDTO;
import com.fang.backgroundapi.service.SysUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fang.backgroundapi.utils.SnowflakeIdWorker;
import nonapi.io.github.classgraph.json.Id;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SysUsers findUserById(String authorId) {
        SysUsers sysUsers = sysUsersMapper.selectById(authorId);
        return sysUsers;
    }

    @Override
    public Integer insertUser(SysUsers sysUsers) {
        return sysUsersMapper.insert(sysUsers);
    }

    @Override
    public Integer updatePassword(SysUsers sysUsers) {

        return null;
    }

    /**
     * Description:
     * @Author: Bernie_fang
     * @Since: 2022/2/27 16:55
     * @param deleted:  3: 查询所有
     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.SysUsers>
     **/
    @Override
    public List<SysUsers> queryUser(Integer deleted) {
        if (deleted == 3){
            List<SysUsers> usersList = sysUsersMapper.selectList(null);
            return usersList;
        }
        QueryWrapper<SysUsers> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", deleted);
        List<SysUsers> usersList = sysUsersMapper.selectList(wrapper);
        return usersList;
    }

}
