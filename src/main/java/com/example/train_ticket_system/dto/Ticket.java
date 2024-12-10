package com.example.train_ticket_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Ticket {
    private Integer id;
    private Integer userId;
    private Integer trainId;
    private String seatType;
    private Integer quantity;
    private String status;
    private Date bookingTime;

}
