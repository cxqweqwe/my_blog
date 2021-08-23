package com.fang.backgroundapi.service;

import com.fang.backgroundapi.pojo.DO.SystemSettings;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-18
 */
public interface SystemSettingsService {

    /**
     * @Description: 更改系统设置
     * @Author: Bernie_fang
     * @Since: 2021/8/22 17:34
     * @param settings:
     * @return: java.lang.Integer
     **/
    public Integer updateSystemSetting(SystemSettings settings);

    /**
     * @Description: 增加系统设置
     * @Author: Bernie_fang
     * @Since: 2021/8/22 17:48
     * @param settings:
     * @return: java.lang.Integer
     **/
    public Integer insertSystemSetting(SystemSettings settings);

    /**
     * @Description: 查询全部设置
     * @Author: Bernie_fang
     * @Since: 2021/8/22 18:03
     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.SystemSettings>
     **/
    public List<SystemSettings> querySystemSetting();

     /**
     * @Description: 查询一个设置
     * @Author: Bernie_fang
     * @Since: 2021/8/22 18:03
     * @param settingName: 设置名称
     * @return: java.util.List<com.fang.backgroundapi.pojo.DO.SystemSettings>
     **/
    public SystemSettings findSystemSetting(String settingName);

}
