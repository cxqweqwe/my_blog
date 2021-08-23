package com.fang.backgroundapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fang.backgroundapi.pojo.DO.SystemSettings;
import com.fang.backgroundapi.mapper.SystemSettingsMapper;
import com.fang.backgroundapi.service.SystemSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bernie_fang
 * @since 2021-08-18
 */
@Service
public class SystemSettingsServiceImpl implements SystemSettingsService {

    @Autowired
    private SystemSettingsMapper settingsMapper;

    @Override
    public Integer updateSystemSetting(SystemSettings settings) {
        int i = settingsMapper.updateById(settings);
        return i;
    }

    @Override
    public Integer insertSystemSetting(SystemSettings settings) {
        int i = settingsMapper.insert(settings);
        return i;
    }

    @Override
    public List<SystemSettings> querySystemSetting() {
        List<SystemSettings> systemSettings = settingsMapper.selectList(null);
        return systemSettings;
    }

    @Override
    public SystemSettings findSystemSetting(String settingName) {
        QueryWrapper<SystemSettings> wrapper = new QueryWrapper<>();
        wrapper.eq("setting_name", settingName);
        SystemSettings settings = settingsMapper.selectOne(wrapper);
        return settings;
    }

}
