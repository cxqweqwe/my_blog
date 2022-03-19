package com.fang.backgroundapi.webSocket;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2022/3/16 21:35
 **/
@Service
public class SessionPool {

    public static Map<String, Session> sessions = new ConcurrentHashMap<>();


    public static void close(String sessionId) throws IOException {
        for (String authorId : sessions.keySet()) {
            Session session = sessions.get(authorId);
            if (session.getId().equals(sessionId)) {
                sessions.remove(sessionId);
                break;
            }
        }
    }

    public static void sendMessage(String sessionId, String message) {
        sessions.get(sessionId).getAsyncRemote().sendText(message);
    }

    public static void sendMessageToAuthor(String authorId, String message) {
        Session session = sessions.get(authorId);
        if (session != null){
            session.getAsyncRemote().sendText(message);
        }
    }

    /**
     * Description: 群发，遍历发送
     *
     * @param message:
     * @Author: Bernie_fang
     * @Since: 2022/3/16 21:50
     * @return: void
     **/
    public static void sendMessage(String message) {
        for (String authorId : sessions.keySet()) {
            sessions.get(authorId).getAsyncRemote().sendText(message);
        }
    }


}
