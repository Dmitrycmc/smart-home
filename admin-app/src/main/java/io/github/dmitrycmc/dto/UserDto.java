package io.github.dmitrycmc.dto;

import io.github.dmitrycmc.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class UserDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private Long[] roleIds;
}
