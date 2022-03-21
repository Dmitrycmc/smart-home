package io.github.dmitrycmc.websocket.dto;

import lombok.Getter;
import lombok.Setter;

public class ToggleDeviceDto extends WebSocketDto {
    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    boolean active;

    public ToggleDeviceDto(Long id, boolean active) {
        super(ActionType.TOGGLE_DEVICE);
        this.id = id;
        this.active = active;
    }
}
