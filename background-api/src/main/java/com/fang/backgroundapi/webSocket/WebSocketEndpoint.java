package com.fang.backgroundapi.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/16 21:14
 **/
@ServerEndpoint(value = "/websocket/{authorId}")
@Component
public class WebSocketEndpoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("authorId") String authorId) {
        // 存储会话
        SessionPool.sessions.put(authorId, session);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println(SessionPool.sessions.size());
        SessionPool.close(session.getId());
        session.close();
    }

    @OnMessage
    public void OnMessage(String message, Session session) {
        SessionPool.sendMessage(message);
    }


}
