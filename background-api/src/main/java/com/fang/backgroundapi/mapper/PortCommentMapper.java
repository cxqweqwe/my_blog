package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.PortComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.PortCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 论坛评论表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface PortCommentMapper extends BaseMapper<PortComment> {

    List<PortCommentVO> queryPortComment(@Param("portId") String portId, @Param("curPage") Integer curPage, @Param("size") Integer size);

    Long CommentCount(@Param("portId") String portId);

}
