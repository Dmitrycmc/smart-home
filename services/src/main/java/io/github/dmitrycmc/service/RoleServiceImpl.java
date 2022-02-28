package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.RoleDao;
import io.github.dmitrycmc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
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
    }
}
