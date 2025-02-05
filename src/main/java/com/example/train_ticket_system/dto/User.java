package com.example.train_ticket_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
}
