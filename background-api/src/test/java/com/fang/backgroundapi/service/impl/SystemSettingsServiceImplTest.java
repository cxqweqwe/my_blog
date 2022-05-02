package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.mapper.SystemSettingsMapper;
import com.fang.backgroundapi.pojo.DO.SystemSettings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/22 17:50
 **/
@SpringBootTest
class SystemSettingsServiceImplTest {

    // @Autowired
    // private SystemSettingsServiceImpl settingsService;

    // @Test
    // void updateSystemSetting() {
    //
    // }

    // @Test
    // void insertSystemSetting() {
    //     SystemSettings settings = new SystemSettings();
    //     settings.setSettingName("allow_phone");
    //     settings.setAllowOrNot(0);
    //     settingsService.insertSystemSetting(settings);
    // }
    //
    // @Test
    // void querySystemSetting() {
    //     List<SystemSettings> systemSettings = settingsService.querySystemSetting();
    //     for (SystemSettings systemSetting : systemSettings) {
    //         if (systemSetting.getSettingName().equals("注册")){
    //             systemSetting.setSettingName("allow_register");
    //             settingsService.updateSystemSetting(systemSetting);
    //             break;
    //         }
    //     }
    //
    // }

}