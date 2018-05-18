package com.zoctan.api.controller;

import com.zoctan.api.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Zoctan
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    private final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 线程安全 Set，用来存放每个客户端对应的 WebSocketServer 对象
     */
    private static final CopyOnWriteArraySet<WebSocketServer> WEB_SOCKET_SET = new CopyOnWriteArraySet<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(final Session session) {
        this.session = session;
        // 加入set中
        WEB_SOCKET_SET.add(this);
        this.log.debug("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 从set中删除
        WEB_SOCKET_SET.remove(this);
        this.log.debug("有一连接关闭");
    }

    public void sendMessage(final String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public void send2All(final String message) {
        this.log.info(message);
        for (final WebSocketServer item : WEB_SOCKET_SET) {
            try {
                item.sendMessage(message);
            } catch (final IOException ignored) {
            }
        }
    }

    @Scheduled(fixedRate = 2000)
    public void sendSomething() {
        if (WEB_SOCKET_SET.isEmpty()) {
            this.log.info("服务器端尝试推送，但是没有客户端连接");
            return;
        }
        this.send2All("服务器端推送 => " + DateUtil.getStringDate());
    }


    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(final String message, final Session session) {
        this.log.debug("来自客户端 => {}", message);
        try {
            session.getBasicRemote().sendText("Hello Client");
        } catch (final IOException ignored) {
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(final Session session, final Throwable error) {
        this.log.error(error.getMessage());
    }

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
