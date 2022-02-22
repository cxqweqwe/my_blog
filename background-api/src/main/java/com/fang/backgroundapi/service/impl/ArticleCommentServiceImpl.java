package com.fang.backgroundapi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.ArticleComment;
import com.fang.backgroundapi.mapper.ArticleCommentMapper;
import com.fang.backgroundapi.pojo.VO.ArticleCommentVO;
import com.fang.backgroundapi.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    public Integer publishComment(ArticleCommentVO articleCommentVO) {
        ArticleComment articleComment = this.VOToDO(articleCommentVO);
        if (!"-1".equals(articleComment.getReply())){
            ArticleComment comment = articleCommentMapper.selectById(articleComment.getReply());
            if ("-1".equals(comment.getFirstComment())){
                articleComment.setFirstComment(comment.getId());
            }else {
                articleComment.setFirstComment(comment.getFirstComment());
            }
        }
        return articleCommentMapper.insert(articleComment);
    }

    /**
     * Description: vo类转到do类
     * @Author: Bernie_fang
     * @Since: 2022/1/26 10:55
     * @param vo:
     * @return: com.fang.backgroundapi.pojo.DO.ArticleComment
     **/
    private ArticleComment VOToDO(ArticleCommentVO vo){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(vo.getArticleId());
        articleComment.setAuthorId(vo.getAuthorId());
        articleComment.setCommentContent(vo.getComment());
        articleComment.setCommentContentHtml(vo.getCommentContentHtml());
        articleComment.setImagePath(vo.getImagePath());
        articleComment.setFirstComment(vo.getFirstComment());
        articleComment.setReply(vo.getBeenCommentedId());
        articleComment.setReplyAuthorId(vo.getBeenCommentedAuthorId());
        articleComment.setState(1);
        return articleComment;
    }

    public List<ArticleCommentVO> showComment(String articleId) {
        ArrayList<ArticleCommentVO> list = new ArrayList<>();
        List<ArticleCommentVO> articleCommentVOS = articleCommentMapper.queryComment(articleId);
        Map<String, List<ArticleCommentVO>> collect =
                articleCommentVOS.stream().collect(Collectors.groupingBy(ArticleCommentVO::getBeenCommentedId));

        List<ArticleCommentVO> firstCommentList = articleCommentVOS.stream().filter((articleComment) ->
                "-1".equals(articleComment.getBeenCommentedId())
        ).collect(Collectors.toList());
        // firstCommentList.forEach(System.out::println);
        for (ArticleCommentVO commentVO : firstCommentList) {
            List<ArticleCommentVO> commentGroup = articleCommentMapper.queryCommentGroup(commentVO.getId());
            List<ArticleCommentVO> sort = commentGroup.stream().sorted(Comparator.comparing(ArticleCommentVO::getTime)).collect(Collectors.toList());
            commentVO.setReplyList(sort);
            list.add(commentVO);
        }
        List<ArticleCommentVO> sortList = list.stream().sorted(Comparator.comparing(ArticleCommentVO::getTime).reversed()).collect(Collectors.toList());
        return sortList;
    }

    @Override
    public Integer deleteComment(String id, String authorId) {
        QueryWrapper<ArticleComment> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .eq("author_id", authorId);
        return articleCommentMapper.delete(wrapper);
    }

    // /**
    //  * Description: do类转到vo类
    //  * @Author: Bernie_fang
    //  * @Since: 2022/1/26 10:57
    //  * @param articleComment:
    //  * @return: com.fang.backgroundapi.pojo.VO.ArticleCommentVO
    //  **/
    // private ArticleCommentVO DOToVO(ArticleComment articleComment){
    //     ArticleCommentVO commentVO = new ArticleCommentVO();
    //     commentVO.setArticleId();
    //     commentVO.setAuthorId();
    //     commentVO.setAvatarPath();
    //     commentVO.setBeenCommentedAuthorId();
    //     commentVO.setBeenCommentedNickName();
    //     commentVO.
    //     return commentVO;
    // }


}
