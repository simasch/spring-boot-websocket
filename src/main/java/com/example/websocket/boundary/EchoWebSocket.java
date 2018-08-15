package com.example.websocket.boundary;

import com.example.websocket.configuration.SpringContext;
import com.example.websocket.entity.Echo;
import com.example.websocket.entity.EchoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class EchoWebSocket {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoWebSocket.class);

    private final EchoRepository echoRepository;

    public EchoWebSocket() {
        this.echoRepository = (EchoRepository) SpringContext.getApplicationContext().getBean("echoRepository");
    }

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("onOpen " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            LOGGER.info("onMessage From=" + session.getId());
            LOGGER.info("onMessage Message=" + message);

            Echo echo = new Echo();
            echo.setText(message);
            echoRepository.saveAndFlush(echo);

            session.getBasicRemote().sendText(message.toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("onClose " + session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        LOGGER.error(t.getMessage(), t);
    }
}