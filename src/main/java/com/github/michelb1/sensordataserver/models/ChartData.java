package com.github.michelb1.sensordataserver.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChartData {

    private final List<Timestamp> datetime;
    private final List<BigDecimal> temperature;
    private final List<BigDecimal> humidity;

    public ChartData() {
        datetime = new ArrayList<>();
        temperature = new ArrayList<>();
        humidity = new ArrayList<>();
    }

    /**
     * @return the datetime
     */
    public List<Timestamp> getDatetime() {
        return datetime;
    }

    /**
     * @return the humidity
     */
    public List<BigDecimal> getHumidity() {
        return humidity;
    }

    /**
     * @return the temperature
     */
    public List<BigDecimal> getTemperature() {
        return temperature;
    }

}
