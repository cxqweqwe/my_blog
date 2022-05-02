package com.fang.backgroundapi;

import com.fang.backgroundapi.service.impl.ScheduledServiceImpl;
import com.fang.backgroundapi.typehandler.Encrypt;
import com.fang.backgroundapi.typehandler.EncryptTypeHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackgroundApiApplicationTests {

    // @Test
    // void contextLoads() {
    //     EncryptTypeHandler encryptTypeHandler = new EncryptTypeHandler();
    //     Encrypt decrypt = encryptTypeHandler.decrypt("448970028@qq.com");
    //     System.out.println(decrypt.getValue());
    // }
    //
    //
    // @Autowired
    // private ScheduledServiceImpl scheduledService;
    //
    //
    // @Test
    // void dailyStatistics() {
    //     for (int i = 0; i < 5; i++) {
    //         scheduledService.dailyStatistics();
    //     }
    // }


}
