package com.example.train_ticket_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainSeat {
    private Integer id;
    private Integer trainId;
    private String seatType;
    private Integer availableSeats;
}
