package com.example.train_ticket_system.mapper;

import com.example.train_ticket_system.dto.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TicketMapper {
    // 插入新订单
    @Insert("INSERT INTO tickets(user_id, train_id, seat_type, quantity, status, booking_time) " +
            "VALUES(#{userId}, #{trainId}, #{seatType}, #{quantity}, #{status}, #{bookingTime})")
    int insertTicket(Ticket ticket);

    // 根据用户ID查询订单
    @Select("SELECT * FROM tickets WHERE user_id = #{userId}")
    List<Ticket> selectTicketsByUserId(@Param("userId") Integer userId);


    // 更新订单状态
    @Update("UPDATE tickets SET status = #{status} WHERE id = #{id}")
    int updateTicketStatus(@Param("id") Integer id, @Param("status") String status);

    // 删除订单（可选）
    @Delete("DELETE FROM tickets WHERE id = #{id}")
    int deleteTicketById(@Param("id") Integer id);

    // 获取订单信息并加行锁（用于取消订单时确保数据一致性）
    @Select("SELECT * FROM tickets WHERE id = #{id} FOR UPDATE")
    Ticket selectTicketById(@Param("id") Integer id);
}
