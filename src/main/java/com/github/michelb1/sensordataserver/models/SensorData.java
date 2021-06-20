package com.github.michelb1.sensordataserver.models;

import java.io.Serializable;

public class SensorData implements Serializable {

    private static final long serialVersionUID = 6813893635150611169L;

    private String temperature;

    private String humidity;

    /**
     * @return the humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * @return the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
