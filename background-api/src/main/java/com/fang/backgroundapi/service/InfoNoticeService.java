package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.InfoNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 信息通知记录表 服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2022-03-19
 */
public interface InfoNoticeService extends IService<InfoNotice> {

    public Integer insertInfoNoticeList(List<InfoNotice> infoNoticeList);

    public Integer updateRead(String beNotified);

    public Long queryUnreadCount(String beNotified);

    public List<InfoNotice> queryInfoNotice(String beNotified);



}
