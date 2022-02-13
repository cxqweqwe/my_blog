package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.PostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.PostInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 论坛的帖子信息 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    List<PostInfoVO> queryPostInfo(@Param("curPage")Integer curPage,
                             @Param("size") Integer size,
                             @Param("keys") List keys);

    List<PostInfoVO> selectPostInfo(@Param("curPage")Integer curPage,
                             @Param("size") Integer size,
                             @Param("postId") String postId,
                             @Param("authorId") String authorId);

    Long countPostInfo(@Param("delete") Integer delete,
                       @Param("state")Integer state,
                       @Param("keys")List keys,
                       @Param("postId") String postId,
                       @Param("authorId") String authorId);

}
