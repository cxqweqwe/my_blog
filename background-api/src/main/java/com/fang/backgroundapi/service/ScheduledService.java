package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.BlogInfoDay;

import java.util.List;

/**
 * @author Bernie_fang
 * @Description: 定时任务接口
 * @create 2022/2/27 14:24
 **/
public interface ScheduledService {

    public void statistics();

    public void dailyStatistics();

}
