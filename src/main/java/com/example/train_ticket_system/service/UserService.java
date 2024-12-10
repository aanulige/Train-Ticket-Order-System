package com.example.train_ticket_system.service;

import com.example.train_ticket_system.dto.User;
import com.example.train_ticket_system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserMapper userMapper;
    public void register(String username,
                        String password,
                        String email) throws Exception {
        if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
            throw new Exception();
        }

        User user = User.builder()
                .userName(username)
                .password(password)
                .email(email)
                .build();

        this.userMapper.insertUser(user);

    }

    public User login(String username, String password) throws Exception {
        User user = this.userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new Exception();
        }
        if(!password.equals(user.getPassword())){
            throw new Exception();
        }
        return user;
    }

}
