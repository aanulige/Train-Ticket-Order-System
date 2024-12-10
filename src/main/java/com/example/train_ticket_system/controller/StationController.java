package com.example.train_ticket_system.controller;

import com.example.train_ticket_system.dto.Station;
import com.example.train_ticket_system.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stations")
public class StationController {
    @Autowired
    private StationService stationService;

    @PostMapping("/add")
    public String addStation(@RequestBody Station station){
        int result = this.stationService.addStation(station.getName(),
                                                    station.getLocation());
        return result > 0 ? "添加成功" : "添加失败";
    }

    // 获取所有车站列表
    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    // 根据名称查询车站
    @GetMapping("/{name}")
    public Station getStationByName(@PathVariable String name) {
        return stationService.getStationByName(name);
    }

    // 删除车站（可选）
    @DeleteMapping("/delete/{id}")
    public String deleteStation(@PathVariable Integer id) {
        int result = stationService.deleteStation(id);
        return result > 0 ? "删除成功" : "删除失败";
    }

    // 更新车站信息（可选）
    @PutMapping("/update")
    public String updateStation(@RequestBody Station station) {
        int result = stationService.updateStation(station);
        return result > 0 ? "更新成功" : "更新失败";
    }

}
