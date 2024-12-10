package com.example.train_ticket_system.service;

import com.example.train_ticket_system.dto.TrainSeat;
import com.example.train_ticket_system.mapper.TrainSeatMapper;
import com.example.train_ticket_system.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitializationService {
    @Autowired
    private TrainSeatMapper trainSeatMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String SEAT_STOCK_KEY_PREFIX = "seat_stock:";
    public void initializeSeatStock() {
        List<TrainSeat> trainSeats = trainSeatMapper.selectAllTrainSeats();
        for (TrainSeat trainSeat : trainSeats) {
            String seatKey = SEAT_STOCK_KEY_PREFIX + trainSeat.getTrainId() + ":" + trainSeat.getSeatType();
            redisUtil.set(seatKey, trainSeat.getAvailableSeats());
        }
    }
}
