package io.github.dmitrycmc.controller;

import io.github.dmitrycmc.dto.RoleDto;
import io.github.dmitrycmc.exception.NotFoundException;
import io.github.dmitrycmc.model.Role;
import io.github.dmitrycmc.model.User;
import io.github.dmitrycmc.service.RoleService;
import io.github.dmitrycmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String getList(Model model) {
        List<Role> roles = roleService.search();
        model.addAttribute("roles", roles.stream().map(role -> {
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setName(role.getName());
            roleDto.setUserLogins(role.getUsers().stream().map(User::getLogin).toArray(String[]::new));
            return roleDto;
        }).collect(Collectors.toList()));
        return "role/table";
    }

    @GetMapping(path = "/new")
    public String create(Model model) {
        model.addAttribute("role", new RoleDto());

        List<User> users = userService.search();
        model.addAttribute("users", users);
        return "role/form";
    }

    @GetMapping(path = "/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Role role = roleService.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setUserLogins(role.getUsers().stream().map(User::getLogin).toArray(String[]::new));
        model.addAttribute("role", roleDto);

        List<User> users = userService.search();
        model.addAttribute("users", users);
        return "role/form";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/role";
    }

    @PostMapping
    public String save(RoleDto roleDto) {
        Role role = new Role();

        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        if (roleDto.getUserLogins() != null) {
            role.setUsers(userService.findAllByLoginIsIn(Arrays.asList(roleDto.getUserLogins())));
        }

        roleService.save(role);
        return "redirect:/role";
    }
}
