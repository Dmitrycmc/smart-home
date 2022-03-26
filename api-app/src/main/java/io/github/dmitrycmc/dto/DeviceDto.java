package io.github.dmitrycmc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class DeviceDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<String> pictures;

    @Getter
    @Setter
    private List<String> previews;
}
