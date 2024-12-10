package com.example.train_ticket_system.service;

import com.example.train_ticket_system.dto.Station;
import com.example.train_ticket_system.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired private StationMapper stationMapper;

    // 添加新车站
    public int addStation(String StationName, String location){
        Station newStation = Station.builder()
                        .name(StationName)
                        .location(location)
                        .build();
        return this.stationMapper.insertStation(newStation);
    }

    // 获取所有车站列表
    public List<Station> getAllStations(){
        return this.stationMapper.selectAllStations();
    }

    // 根据名称查询车站
    public Station getStationByName(String StationName){
        return this.stationMapper.selectStationByName(StationName);
    }

    // 删除车站（可选）
    public int deleteStation(Integer id) {
        return stationMapper.deleteStationById(id);
    }
    // 更新车站信息（可选）
    public int updateStation(Station station) {
        return stationMapper.updateStation(station);
    }
}
