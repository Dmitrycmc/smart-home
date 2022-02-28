package io.github.dmitrycmc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
