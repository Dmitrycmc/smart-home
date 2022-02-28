package io.github.dmitrycmc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class RoleDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String[] userLogins = new String[] {};
}
