package com.fang.backgroundapi.service;


import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.BlogInfo;

/**
 * <p>
 * 博客信息统计表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
public interface BlogInfoService {

    public void likeBlog(String authorId, String article) throws MyException;

    public void unlikeBlog(String authorId, String article) throws MyException;

    public void readBlog(String article);

    public void collectionBlog(String authorId, String article);

    public void unCollectionBlog(String authorId, String article);

    public BlogInfo isCollectionBlog(String authorId, String article);

    public BlogInfo findBlogInfo(String article);


}
