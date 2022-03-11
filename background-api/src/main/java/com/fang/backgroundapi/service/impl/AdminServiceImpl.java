package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.mapper.AdminMapper;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.VO.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/10 21:52
 **/
@Service
public class AdminServiceImpl {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    public PagingData queryUser(Integer curPage, Integer size) {
        curPage = (curPage - 1) * size;
        List<AdminUser> list = adminMapper.queryUser(curPage, size);
        int total = sysUsersService.count();
        return new PagingData(Long.valueOf(total), list);
    }

    /**
     * Description:
     * @Author: Bernie_fang
     * @Since: 2022/3/10 22:17
     * @param authorId:
     * @param status:  1:删除  2: 封禁
     * @return: boolean
     **/
    public Integer changeUser(String authorId, Integer status) {
        return adminMapper.changeUser(authorId, status);
    }


}
