package com.swd.springlearn.util;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author swd
 * @ClassName: WebSocketUtils
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/1817:19
 */
public class WebSocketUtils {
    //模拟存储websocket的session
    public static final Map<String, Session> LIVING_SESSION_CACHE = new ConcurrentHashMap<>();

    public static void sendMessageAll(String message){
        LIVING_SESSION_CACHE.forEach((sessionId, session)->sendMessage(session,message));
    }

    /**
     * 给指定用户发送消息
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        if(session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null){
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
