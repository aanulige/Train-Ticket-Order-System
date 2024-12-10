package com.example.train_ticket_system.mapper;

import com.example.train_ticket_system.dto.TrainSeat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TrainSeatMapper {

    // 获取座位信息并加行锁（用于预订）
    @Select("SELECT * FROM train_seats WHERE train_id = #{trainId} AND seat_type = #{seatType} FOR UPDATE")
    TrainSeat selectSeatForUpdate(@Param("trainId") Integer trainId, @Param("seatType") String seatType);

    // 扣减库存
    @Update("UPDATE train_seats SET available_seats = available_seats - #{quantity} WHERE id = #{id} AND available_seats >= #{quantity}")
    int decreaseAvailableSeats(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 增加库存（用于取消订单）
    @Update("UPDATE train_seats SET available_seats = available_seats + #{quantity} WHERE id = #{id}")
    int increaseAvailableSeats(@Param("id") Integer id, @Param("quantity") Integer quantity);

    // 初始化座位信息（可选）
    @Insert("INSERT INTO train_seats(train_id, seat_type, available_seats) VALUES(#{trainId}, #{seatType}, #{availableSeats})")
    int insertTrainSeat(TrainSeat trainSeat);

    // 扣减库存
    @Update("UPDATE train_seats SET available_seats = available_seats - #{quantity} WHERE train_id = #{trainId} AND seat_type = #{seatType} AND available_seats >= #{quantity}")
    int decreaseAvailableSeatsById(@Param("trainId") Integer trainId, @Param("seatType") String seatType, @Param("quantity") Integer quantity);

    // 增加库存
    @Update("UPDATE train_seats SET available_seats = available_seats + #{quantity} WHERE train_id = #{trainId} AND seat_type = #{seatType}")
    int increaseAvailableSeatsById(@Param("trainId") Integer trainId, @Param("seatType") String seatType, @Param("quantity") Integer quantity);

    @Select("SELECT * FROM train_seats")
    List<TrainSeat> selectAllTrainSeats();
}
