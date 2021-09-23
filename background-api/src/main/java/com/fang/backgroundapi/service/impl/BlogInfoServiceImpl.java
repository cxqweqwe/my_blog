package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.mapper.BlogInfoMapper;
import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.pojo.DO.LikesRecord;
import com.fang.backgroundapi.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 博客信息统计表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Autowired
    private LikesRecordServiceImpl likesRecordService;

    /**
     * @param authorId  :
     * @param articleId :
     * @Description: 博客点赞 需要更新两个表。 开启事务
     * @Author: Bernie_fang
     * @Since: 2021/9/18 13:46
     **/
    @Transactional(propagation = Propagation.REQUIRED, value = "detTransactionManager",
            rollbackFor = {Exception.class, RuntimeException.class, MyException.class})
    public void likeBlog(String authorId, String articleId) throws MyException {
        LikesRecord record = likesRecordService.findLikeRecord(authorId, articleId);
        if (record != null){
            throw new MyException("您已经为此博客点赞了", 6000);
        }
        blogInfoMapper.likeBlog(articleId);
        likesRecordService.recodeLikeBlog(authorId, articleId);
    }

    /**
     * @param authorId:
     * @param articleId:
     * @Description: 博客取消点赞
     * @Author: Bernie_fang
     * @Since: 2021/9/18 13:46
     * @return: com.fang.backgroundapi.common.void
     **/
    @Override
    public void unlikeBlog(String authorId, String articleId) throws MyException {
        LikesRecord record = likesRecordService.findLikeRecord(authorId, articleId);
        if (record == null){
            throw new MyException("您并未为此博客点赞", 6000);
        }
        blogInfoMapper.unLikeBlog(articleId);
        likesRecordService.deleteRecode(record.getId());
    }

    /**
     * @param articleId:
     * @Description:
     * @Author: Bernie_fang
     * @Since: 2021/9/18 13:46
     * @return: com.fang.backgroundapi.common.void
     **/
    @Override
    public void readBlog(String articleId) {
        blogInfoMapper.readBlog(articleId);
    }

    /**
     * @param authorId:
     * @param articleId:
     * @Description:
     * @Author: Bernie_fang
     * @Since: 2021/9/18 13:46
     * @return: com.fang.backgroundapi.common.void
     **/
    @Override
    public void collectionBlog(String authorId, String articleId) {

    }

    /**
     * @param authorId:
     * @param articleId:
     * @Description:
     * @Author: Bernie_fang
     * @Since: 2021/9/18 13:46
     * @return: com.fang.backgroundapi.common.void
     **/
    @Override
    public void unCollectionBlog(String authorId, String articleId) {

    }

    @Override
    public BlogInfo isCollectionBlog(String authorId, String articleId) {

        return null;
    }



    @Override
    public BlogInfo findBlogInfo(String articleId) {
        QueryWrapper<BlogInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",articleId);
        BlogInfo blogInfo = blogInfoMapper.selectOne(wrapper);
        return blogInfo;
    }


}
