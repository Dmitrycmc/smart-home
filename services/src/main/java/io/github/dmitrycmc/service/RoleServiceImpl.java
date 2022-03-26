package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.RoleDao;
import io.github.dmitrycmc.dao.UserDao;
import io.github.dmitrycmc.model.Role;
import io.github.dmitrycmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;
    private final UserDao userDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    public List<Role> search() {
        return roleDao.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAllByNameIsIn(List<String> names) {
        return roleDao.findAllByNameIsIn(names);
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);

        userDao.findAll().forEach(u -> {
            boolean roleTurnedOn = role.getUsers().stream().map(User::getLogin).collect(Collectors.toList()).contains(u.getLogin());

            Optional<Role> userRole = u.getRoles().stream().filter(r -> r.getId().equals(role.getId())).findFirst();

            boolean roleWasTurnedOn = userRole.isPresent();

            if (roleWasTurnedOn && !roleTurnedOn) {
                u.getRoles().remove(userRole.get());
            }
            if (!roleWasTurnedOn && roleTurnedOn) {
                u.getRoles().add(role);
            }

            userDao.save(u);
        });
    }
}
