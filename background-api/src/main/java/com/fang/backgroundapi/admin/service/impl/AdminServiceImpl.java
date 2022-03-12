package com.fang.backgroundapi.admin.service.impl;

import com.fang.backgroundapi.common.PagingData;
import com.fang.backgroundapi.admin.mapper.AdminMapper;
import com.fang.backgroundapi.mapper.ArticleMapper;
import com.fang.backgroundapi.pojo.VO.AdminArticle;
import com.fang.backgroundapi.pojo.VO.AdminUser;
import com.fang.backgroundapi.service.impl.SysUsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private ArticleMapper articleMapper;

    public PagingData queryUser(Integer curPage, Integer size) {
        curPage = (curPage - 1) * size;
        List<AdminUser> list = adminMapper.queryUser(curPage, size);
        int total = sysUsersService.count();
        return new PagingData(Long.valueOf(total), list);
    }

    /**
     * Description:
     *
     * @param authorId:
     * @param status:   1:删除  2: 封禁
     * @Author: Bernie_fang
     * @Since: 2022/3/10 22:17
     * @return: boolean
     **/
    public Integer changeUser(String authorId, Integer status) {
        return adminMapper.changeUser(authorId, status, new Date());
    }

    public PagingData queryArticle(Integer curPage, Integer size) {
        curPage = (curPage - 1) * size;
        List<AdminArticle> adminArticles = adminMapper.queryArticle(curPage, size);
        Long total = adminMapper.countArticle();
        return new PagingData(total, adminArticles);
    }

    public Integer trialArticle(String articleId, Integer status) {
        adminMapper.trialArticle(articleId, status, new Date());
        return null;
    }


}
