package com.fang.backgroundapi.mapper;

import com.fang.backgroundapi.pojo.DO.InfoNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 信息通知记录表 Mapper 接口
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-03-19
 */
@Repository
public interface InfoNoticeMapper extends BaseMapper<InfoNotice> {

    public Integer insertInfoNoticeList(List<InfoNotice> infoNoticeList);

    public Long queryUnreadCount(@Param("beNotified") String beNotified);

}
