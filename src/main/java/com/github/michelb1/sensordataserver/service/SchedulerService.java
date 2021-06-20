package com.github.michelb1.sensordataserver.service;

import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private SensorDataService service;

    @Value("${sensor.service.url}")
    private String sensorServiceUri;

    @Value("${sensor.service.active}")
    private boolean active;

    @Scheduled(cron = "0 * * * * ?")
    public void readSensorData() {

        if(!active){
            return;
        }

        LOG.info("read Sensor Data - {}", DATE_TIME_FORMATTER.format(LocalDateTime.now()));

        RestTemplate template = new RestTemplate();
        TemperatureHumidity data = template.getForObject(sensorServiceUri, TemperatureHumidity.class);

        data.setId(null);
        data.setReadTime(new Timestamp(new Date().getTime()));

        service.persistSensorData(data);
    }

}
