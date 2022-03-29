package io.github.dmitrycmc.websocket.dto;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

public class ToggleDeviceDto extends WebSocketDto {
    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    boolean active;

    public static ToggleDeviceDto fromJson(String json) {
        Gson g = new Gson();
        return g.fromJson(json, ToggleDeviceDto.class);
    }

    public ToggleDeviceDto(Long id, boolean active) {
        super(ActionType.TOGGLE_DEVICE);
        this.id = id;
        this.active = active;
    }
}
