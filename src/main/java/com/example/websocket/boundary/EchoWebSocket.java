package com.example.websocket.boundary;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class EchoWebSocket {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId());

        try {
            session.getBasicRemote().sendText(message.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}