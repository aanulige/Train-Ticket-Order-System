package com.example.train_ticket_system.controller;

import com.example.train_ticket_system.dto.Train;
import com.example.train_ticket_system.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // 添加新车次
    @PostMapping("/add")
    public String addTrain(@RequestBody Train train) {
        int result = trainService.addTrain(train);
        return result > 0 ? "添加成功" : "添加失败";
    }

    // 获取所有车次列表
    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    // 根据车次号查询车次
    @GetMapping("/{trainNumber}")
    public Train getTrainByNumber(@PathVariable String trainNumber) {
        return trainService.getTrainByNumber(trainNumber);
    }

    // 根据出发站、到达站和日期查询车次
    @GetMapping("/search")
    public List<Train> getTrainsByStationsAndDate(@RequestParam("departureStationId") Integer departureStationId,
                                                  @RequestParam("arrivalStationId") Integer arrivalStationId,
                                                  @RequestParam("departureDate") String departureDate) {
        return trainService.getTrainsByStationsAndDate(departureStationId, arrivalStationId, departureDate);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrain(@PathVariable Integer id) {
        int result = trainService.deleteTrain(id);
        return result > 0 ? "删除成功" : "删除失败";
    }

    @PutMapping("/update")
    public String updateTrain(@RequestBody Train train) {
        int result = trainService.updateTrain(train);
        return result > 0 ? "更新成功" : "更新失败";
    }
}

