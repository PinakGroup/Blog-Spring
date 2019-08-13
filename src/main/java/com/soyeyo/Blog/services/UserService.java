package com.soyeyo.Blog.services;

import com.soyeyo.Blog.data.UserRepository;
import com.soyeyo.Blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(users.save(user) != null){
            return true;
        }
        return false;
    }

    public User findByUsername(String username) {
        return users.findByUsername(username);
    }
}
