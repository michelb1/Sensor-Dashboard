package com.github.michelb1.sensordataserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import com.github.michelb1.sensordataserver.models.ChartData;
import com.github.michelb1.sensordataserver.models.SensorData;
import com.github.michelb1.sensordataserver.repository.TemperatureHumidityRepository;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

@Service
public class SensorDataService {

    private static final Logger LOG = LoggerFactory.getLogger(SensorDataService.class);

    @Autowired
    private TemperatureHumidityRepository repo;

    @Autowired
    private ChartDataMapper mapper;

    @Autowired
    private WebsocketHandler websocketHandler;

    @Transactional(propagation = Propagation.SUPPORTS)
    public ChartData getLatestEntries(int limit) {
        var data = mapper.mapEntityToModel(repo.findTop30ByOrderByIdDesc(), 1, true);
        return data;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ChartData getCompleteData() {
        var resolution = 60;
        var data = mapper.mapEntityToModel(repo.findAllByOrderByIdAsc(), resolution, false);

        return data;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ChartData getDataFilteredByDate(Date dateFrom, Date dateTo) {
        int resolution = 10;

        var time = LocalDateTime.now().minusHours(24);
        var data = mapper.mapEntityToModel(repo.findByReadTimeGreaterThanOrderByIdAsc(Timestamp.valueOf(time)), resolution, false);

        return data;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void persistSensorData(TemperatureHumidity data) {
        var savedData = repo.save(data);
        notifiyClients(savedData);

        LOG.info("inserted: " + savedData);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TemperatureHumidity persistSensorDataRequest(SensorData data) {
        var entitiy = new TemperatureHumidity(null,
                new Timestamp(new Date().getTime()), data.getTemperature(), data.getHumidity());

        var savedData = repo.save(entitiy);
        notifiyClients(savedData);

        LOG.info("inserted: " + savedData);
        return savedData;
    }

    private void notifiyClients(TemperatureHumidity data) {

        var chartData = mapper.mapEntityToModel(data);
        var objMapper = new ObjectMapper();

        try {
            var json = objMapper.writeValueAsString(chartData);
            var message = new TextMessage(json);

            websocketHandler.sendMessage(message);
        } catch (IOException io) {
            LOG.error("Websocket IO-Exception", io);
        }
    }
}
