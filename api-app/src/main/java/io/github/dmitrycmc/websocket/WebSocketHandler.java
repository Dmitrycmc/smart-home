package io.github.dmitrycmc.websocket;

import io.github.dmitrycmc.service.DeviceService;
import io.github.dmitrycmc.websocket.dto.ToggleDeviceDto;
import io.github.dmitrycmc.websocket.dto.WebSocketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final DeviceService deviceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Autowired
    public WebSocketHandler(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        LOGGER.info("Client connected: " + session.getId());

        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        LOGGER.info("Client disconnected: " + session.getId());

        super.afterConnectionClosed(session, status);
    }

    private void handleToggleDevice(TextMessage message) {
        ToggleDeviceDto toggleDeviceDto = ToggleDeviceDto.fromJson(message.getPayload());

        deviceService.setActive(toggleDeviceDto.getId(), toggleDeviceDto.isActive());

        sessions.forEach(s -> {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                LOGGER.info("Unable to send message: " + s.getId());
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WebSocketDto webSocketDto = WebSocketDto.fromJson(message.getPayload());

        if (webSocketDto.getActionType() == WebSocketDto.ActionType.TOGGLE_DEVICE) {
            this.handleToggleDevice(message);
        } else {
            LOGGER.warn("Unknown action of message: " + message.getPayload());
        }

        super.handleTextMessage(session, message);
    }
}