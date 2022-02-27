package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.BO.StatisticsBO;
import com.fang.backgroundapi.pojo.DO.BlogInfoDay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 博客信息统计，每天 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-02-27
 */
@Repository
public interface BlogInfoDayMapper extends BaseMapper<BlogInfoDay> {

    public BlogInfoDay statisticsPLC(@Param("authorId") String authorId);

    public Integer statisticsSubscription(@Param("authorId") String authorId);

    public Integer statisticsComment(@Param("authorId") String authorId);

}
