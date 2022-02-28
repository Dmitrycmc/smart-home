package io.github.dmitrycmc.security;

import io.github.dmitrycmc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByLogin(username)
                .map(user -> new User(
                    user.getLogin(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
