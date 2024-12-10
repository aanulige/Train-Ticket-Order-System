package com.example.train_ticket_system.service;

import com.example.train_ticket_system.dto.Train;
import com.example.train_ticket_system.mapper.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    private TrainMapper trainMapper;

    // 添加新车次
    public int addTrain(Train train) {
        // 在此可以添加数据验证和处理
        return trainMapper.insertTrain(train);
    }

    // 获取所有车次列表
    public List<Train> getAllTrains() {
        return trainMapper.selectAllTrains();
    }

    // 根据车次号查询车次
    public Train getTrainByNumber(String trainNumber) {
        return trainMapper.selectTrainByNumber(trainNumber);
    }

    // 根据出发站、到达站和日期查询车次
    public List<Train> getTrainsByStationsAndDate(Integer departureStationId, Integer arrivalStationId, String departureDate) {
        return trainMapper.selectTrainsByStationsAndDate(departureStationId, arrivalStationId, departureDate);
    }

    // 删除车次（可选）
    public int deleteTrain(Integer id) {
        return trainMapper.deleteTrainById(id);
    }

    // 更新车次信息（可选）
    public int updateTrain(Train train) {
        return trainMapper.updateTrain(train);
    }
}
