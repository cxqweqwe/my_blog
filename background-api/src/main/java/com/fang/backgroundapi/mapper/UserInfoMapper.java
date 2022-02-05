package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fang.backgroundapi.pojo.VO.BloggerVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-15
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    public BloggerVO gerBlogger(@Param("authorId") String authorId);

}
