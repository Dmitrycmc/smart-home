package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.dto.UserDto;
import io.github.dmitrycmc.exception.NotFoundException;
import io.github.dmitrycmc.model.Role;
import io.github.dmitrycmc.model.User;
import io.github.dmitrycmc.service.RoleService;
import io.github.dmitrycmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping
    public String getList(Model model) {
        List<User> users = userService.search();
        model.addAttribute("users", users.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setRoleNames(user.getRoles().stream().map(Role::getName).toArray(String[]::new));
            return userDto;
        }).collect(Collectors.toList()));
        return "user/table";
    }

    @GetMapping(path = "/new")
    public String create(Model model) {
        model.addAttribute("user", new UserDto());

        List<Role> roles = roleService.search();
        model.addAttribute("roles", roles);
        return "user/form";
    }

    @GetMapping(path = "/{id}")
    public String edit(Model model, @PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoleNames(user.getRoles().stream().map(Role::getName).toArray(String[]::new));
        model.addAttribute("user", userDto);

        List<Role> roles = roleService.search();
        model.addAttribute("roles", roles);
        return "user/form";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }

    @PostMapping
    public String save(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(encoder.encode(userDto.getPassword()));

        if (userDto.getRoleNames() != null) {
            user.setRoles(roleService.findAllByNameIsIn(Arrays.asList(userDto.getRoleNames())));
        }

        userService.save(user);
        return "redirect:/user";
    }
}
