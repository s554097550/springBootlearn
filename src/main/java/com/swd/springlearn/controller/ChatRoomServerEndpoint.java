package com.swd.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;

import static com.swd.springlearn.util.WebSocketUtils.LIVING_SESSION_CACHE;
import static com.swd.springlearn.util.WebSocketUtils.sendMessage;
import static com.swd.springlearn.util.WebSocketUtils.sendMessageAll;

/**
 * @author swd
 * @ClassName: ChatRoomServerEndpoint
 * @ProjectName springlearn
 * @Description: TODO
 * @date 2018/9/1817:49
 */
@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    @OnOpen
    public void openSession(@PathParam("username") String userName, Session session){
        LIVING_SESSION_CACHE.put(userName, session);
        String message = "欢迎【"+userName+"】加入聊天室";
        log.info(message);
        sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String userName, String message){
        log.info(message);
        sendMessageAll("用户"+userName+":"+message);
    }

    @OnClose
    public void onClose(@PathParam("username") String userName, Session session){
        LIVING_SESSION_CACHE.remove(userName);
        sendMessageAll("用户【"+userName+"】离开了聊天室");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void OnError(Session session, Throwable throwable){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    @GetMapping("/chat-room/{sender}/to/{receive}")
    public void onMessage(@PathVariable("sender") String sender, @PathVariable("receive") String receive, String message){
        System.out.printf("1111");
        sendMessage(LIVING_SESSION_CACHE.get(receive), "【"+sender+"】->【"+receive+"】："+message);
    }
}
