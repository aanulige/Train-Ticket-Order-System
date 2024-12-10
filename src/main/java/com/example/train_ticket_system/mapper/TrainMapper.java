package com.example.train_ticket_system.mapper;

import com.example.train_ticket_system.dto.Train;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TrainMapper {
    // 插入新车次
    @Insert("INSERT INTO trains (train_number, departure_station_id, arrival_station_id, departure_time, arrival_time, duration)" +
            "VALUES(#{trainNumber}, #{departureStationId}, #{arrivalStationId}, #{departureTime}, #{arrivalTime}, #{duration})")
    int insertTrain(Train train);

    @Select("SELECT * FROM trains")
    List<Train> selectAllTrains();

    // 根据车次号查询车次
    @Select("SELECT * FROM trains WHERE train_number = #{trainNumber}")
    Train selectTrainByNumber(String trainNumber);

    // 根据出发站、到达站和日期查询车次
    @Select("SELECT * FROM trains WHERE departure_station_id = #{departureStationId} AND arrival_station_id = #{arrivalStationId}" +
            " AND DATE(departure_time) = #{departureDate}")
    List<Train> selectTrainsByStationsAndDate(@Param("departureStationId") Integer departureStationId,
                                              @Param("arrivalStationId") Integer arrivalStationId,
                                              @Param("departureDate") String departureDate);

    // 删除车次（可选）
    @Delete("DELETE FROM trains WHERE id = #{id}")
    int deleteTrainById(@Param("id") Integer id);

    // 更新车次信息（可选）
    @Update("UPDATE trains SET train_number = #{trainNumber}, departure_station_id = #{departureStationId}, " +
            "arrival_station_id = #{arrivalStationId}, departure_time = #{departureTime}, " +
            "arrival_time = #{arrivalTime}, duration = #{duration} WHERE id = #{id}")
    int updateTrain(Train train);


    // 获取车次的座位余量
    @Select("SELECT available_seats FROM trains WHERE id = #{trainId} AND seat_type = #{seatType} FOR UPDATE")
    int getAvailableSeats(@Param("trainId") Integer trainId, @Param("seatType") String seatType);

    // 更新座位库存
    @Update("UPDATE trains SET available_seats = available_seats - #{quantity}" +
            " WHERE id = #{trainId} AND seat_type = #{seatType} AND available_seats >= #{quantity}")
    int updateSeats(@Param("trainId") Integer trainId, @Param("seatType") String seatType, @Param("quantity") Integer quantity);
}
