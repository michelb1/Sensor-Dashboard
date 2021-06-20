package com.github.michelb1.sensordataserver.controller;

import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import com.github.michelb1.sensordataserver.models.ChartData;
import com.github.michelb1.sensordataserver.models.SensorData;
import com.github.michelb1.sensordataserver.service.SensorDataService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    @Autowired
    private SensorDataService service;

    @GetMapping("/data-all")
    @ResponseBody
    public ChartData getDataAll() {
        return service.getCompleteData();
    }

    @GetMapping("/data")
    @ResponseBody
    public ChartData getDataFilteredByDate() {
        Date dateFrom = new Date();
        Date dateTo = new Date();

        return service.getDataFilteredByDate(dateFrom, dateTo);
    }

    @GetMapping("/data-latest")
    @ResponseBody
    public ChartData insertData(@RequestParam int limit) {
        return service.getLatestEntries(limit);
    }

    @PostMapping("/insert")
    @ResponseBody
    public TemperatureHumidity insertData(@RequestBody SensorData sensorData) {
        var entity = service.persistSensorDataRequest(sensorData);

        return entity;
    }

}
