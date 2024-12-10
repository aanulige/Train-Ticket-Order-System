package com.example.train_ticket_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Train {
    private Integer id;
    private String trainNumber;
    private Integer departureStationId;
    private Integer arrivalStationId;
    private Date departureTime;
    private Date arrivalTime;
    private Integer duration;

}
