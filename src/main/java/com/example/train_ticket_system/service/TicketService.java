package com.example.train_ticket_system.service;

import com.example.train_ticket_system.dto.Ticket;
import com.example.train_ticket_system.dto.TrainSeat;
import com.example.train_ticket_system.mapper.TicketMapper;
import com.example.train_ticket_system.mapper.TrainSeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.train_ticket_system.util.RedisUtil;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TrainSeatMapper trainSeatMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String SEAT_STOCK_KEY_PREFIX = "seat_stock:";

    // 预订车票
    @Transactional
    public String bookTicket(Ticket ticket) {

        String seatKey = SEAT_STOCK_KEY_PREFIX + ticket.getTrainId() + ":" + ticket.getSeatType();

        // 检查 Redis 中的库存
        Object stockObj = redisUtil.get(seatKey);
        if (stockObj == null) {
            return "Stock has not initialized.";
        }
        int stock = Integer.parseInt(stockObj.toString());

        if (stock < ticket.getQuantity()) {
            return "Tickects are not enough, please check other trains.";
        }

        // 使用 Redis 事务原子性操作扣减库存
        List<Object> result = redisUtil.redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            @SuppressWarnings("unchecked")
            public List<Object> execute(@NonNull RedisOperations operations) throws DataAccessException {
                // Watch the seatKey to detect any changes before the transaction
                operations.watch(seatKey);
                // Start the transaction
                operations.multi();
                // Queue the decrement operation
                operations.opsForValue().decrement(seatKey, ticket.getQuantity());
                // Execute the transaction
                return operations.exec();
            }
        });


        if (result == null || !result.isEmpty()) {
            return "Book failed, please try again.";
        }

        // 插入订单到数据库
        ticket.setStatus("Already Booked");
        int insertResult = ticketMapper.insertTicket(ticket);
        if (insertResult > 0) {
            return "Book successful";
        } else {
            // 插入订单失败，回滚 Redis 库存
            redisUtil.redisTemplate.opsForValue().increment(seatKey, ticket.getQuantity());
            return "Book Failed";
        }

    }

    
    // 根据用户ID获取订单列表
    public List<Ticket> getTicketsByUserId(Integer userId) {
        return ticketMapper.selectTicketsByUserId(userId);
    }

    // 取消订单
    @Transactional
    public String cancelTicket(Integer id) {
        // 获取订单信息
        Ticket ticket = ticketMapper.selectTicketById(id);
        if (ticket == null) {
            return "Order does not exist";
        }

        // 更新订单状态为已取消
        int result = ticketMapper.updateTicketStatus(id, "Cancelled");
        if (result == 0) {
            return "Cancel Failed";
        }
        String seatKey = SEAT_STOCK_KEY_PREFIX + ticket.getTrainId() + ":" + ticket.getSeatType();

        // 增加库存
        try {
            redisUtil.redisTemplate.opsForValue().increment(seatKey, ticket.getQuantity());
            TrainSeat trainSeat = trainSeatMapper.selectSeatForUpdate(ticket.getTrainId(), ticket.getSeatType());
            if (trainSeat != null) {
                trainSeatMapper.increaseAvailableSeats(trainSeat.getId(), ticket.getQuantity());
            }
        } catch (Exception e) {
            System.out.println(e);

        }

        return "Cancel Success";
    }

    // 获取订单详情
    public Ticket getTicketById(Integer id) {
        return ticketMapper.selectTicketById(id);
    }
}
