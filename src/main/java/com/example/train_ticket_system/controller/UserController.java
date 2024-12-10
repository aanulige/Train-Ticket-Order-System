package com.example.train_ticket_system.controller;

import com.example.train_ticket_system.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.train_ticket_system.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService userService;
    public String register(@RequestBody User user) throws Exception {
        this.userService.register(user.getUserName(),
                                  user.getPassword(),
                                  user.getEmail());
        return "Register Success";
    }

    public String login(@RequestBody User user) throws Exception {
        User loggedInUser = this.userService.login(user.getUserName(), user.getPassword());
        return loggedInUser != null ? "登录成功" : "用户名或密码错误";
    }


}
