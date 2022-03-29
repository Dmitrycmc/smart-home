package io.github.dmitrycmc.websocket.dto;

import com.google.gson.Gson;
import lombok.Getter;

public class WebSocketDto extends ToJson {
    public enum ActionType {
        TOGGLE_DEVICE
    }

    @Getter
    protected ActionType actionType;

    public static WebSocketDto fromJson(String json) {
        Gson g = new Gson();
        return g.fromJson(json, WebSocketDto.class);
    }

    public WebSocketDto(ActionType actionType) {
        this.actionType = actionType;
    }
}
