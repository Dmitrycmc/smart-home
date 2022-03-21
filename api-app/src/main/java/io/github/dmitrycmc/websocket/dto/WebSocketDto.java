package io.github.dmitrycmc.websocket.dto;

public class WebSocketDto extends ToJson {
    public enum ActionType {
        TOGGLE_DEVICE
    }

    protected ActionType actionType;

    public WebSocketDto(ActionType actionType) {
        this.actionType = actionType;
    }
}
