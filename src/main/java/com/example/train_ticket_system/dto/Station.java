package com.example.train_ticket_system.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Station{
    private Integer id;
    private String name;
    private String location;
}

