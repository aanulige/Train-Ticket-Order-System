package com.example.train_ticket_system.mapper;

import com.example.train_ticket_system.dto.Station;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StationMapper {

    // 插入新车站
    @Insert("INSERT INTO stations(name, location) VALUES(#{name}, #{location})")
    int insertStation(Station station);

    // 查询所有车站
    @Select("SELECT * FROM stations")
    List<Station> selectAllStations();

    // 根据名称查询车站
    @Select("SELECT * FROM stations WHERE name = #{name}")
    Station selectStationByName(@Param("name") String name);

    // 删除车站（可选）
    @Delete("DELETE FROM stations WHERE id = #{id}")
    int deleteStationById(@Param("id") Integer id);

    // 更新车站信息（可选）
    @Update("UPDATE stations SET name = #{name}, location = #{location} WHERE id = #{id}")
    int updateStation(Station station);
}

