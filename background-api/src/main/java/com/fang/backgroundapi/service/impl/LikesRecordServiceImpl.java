package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.BlogInfo;
import com.fang.backgroundapi.pojo.DO.LikesRecord;
import com.fang.backgroundapi.mapper.LikesRecordMapper;
import com.fang.backgroundapi.service.LikesRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞记录表，记录用户对博客文章的点赞 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class LikesRecordServiceImpl implements LikesRecordService {

    @Autowired
    LikesRecordMapper likesRecordMapper;

    /**
     * @Description: 记录喜欢点赞
     * @Author: Bernie_fang
     * @Since: 2021/9/18 14:25
     * @param authorId: 作者ID
     * @param articleId: 博客文章ID
     * @return: java.lang.Integer
     **/
    @Override
    public Integer recodeLikeBlog(String authorId, String articleId) {
        LikesRecord record = new LikesRecord();
        record.setArticleId(articleId);
        record.setAuthorId(authorId);
        return likesRecordMapper.insert(record);
    }

    /**
     * @Description: 删除喜欢记录
     * @Author: Bernie_fang
     * @Since: 2021/9/18 14:23
     * @param id: likes_record对应的id
     * @return: java.lang.Integer
     **/
    @Override
    public Integer deleteRecode(String id) {
        return likesRecordMapper.deleteById(id);
    }

    /**
     * @Description: 查找点赞记录，没有找到说明没有点赞
     * @Author: Bernie_fang
     * @Since: 2021/9/18 14:38
     * @param authorId:
     * @param articleId:
     * @return: com.fang.backgroundapi.pojo.DO.LikesRecord
     **/
    @Override
    public LikesRecord findLikeRecord(String authorId, String articleId) {
        QueryWrapper<LikesRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", authorId)
                .eq("article_id", articleId);
        LikesRecord likesRecord = likesRecordMapper.selectOne(wrapper);
        return likesRecord;
    }

}
