package com.example.train_ticket_system.mapper;

import com.example.train_ticket_system.dto.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("INSERT INTO users(username, password, email) VALUES(#{username}, #{password}, #{email})")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Select("SELECT * FROM users WHERE username=#{usernameOrEmail} OR email=#{usernameOrEmail}")
    User selectByUsernameOrEmail(String usernameOrEmail);
}
