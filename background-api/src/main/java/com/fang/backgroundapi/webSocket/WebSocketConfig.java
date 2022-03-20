package com.fang.backgroundapi.webSocket;

import com.fang.backgroundapi.service.impl.InfoNoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/16 21:16
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    /**
     * 因 SpringBoot WebSocket 对每个客户端连接都会创建一个 WebSocketServer（@ServerEndpoint 注解对应的）
     * 对象，Bean 注入操作会被直接略过，因而手动注入一个全局变量
     *
     * @param infoNoticeService
     */
    @Autowired
    public void setMessageService(InfoNoticeServiceImpl infoNoticeService) {
        WebSocketEndpoint.infoNoticeService = infoNoticeService;
    }

}
