package io.github.dmitrycmc.service;

import io.github.dmitrycmc.dao.UserDao;
import io.github.dmitrycmc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> search() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAllByLoginIsIn(List<String> logins) {
        return userDao.findAllByLoginIsIn(logins);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
