package com.github.michelb1.sensordataserver.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temperature_humidity")
public class TemperatureHumidity implements Serializable {

    private static final long serialVersionUID = -1435730856691540866L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "read_time")
    private Timestamp readTime;
    private String temp;
    private String humidity;

    public TemperatureHumidity() {
    }

    public TemperatureHumidity(Long id, Timestamp time, String temp, String humidity) {
        this.id = id;
        this.readTime = time;
        this.temp = temp;
        this.humidity = humidity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getReadTime() {
        return this.readTime;
    }

    public void setReadTime(Timestamp time) {
        this.readTime = time;
    }

    public String getTemp() {
        return this.temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "{"
                + " id='" + getId() + "'"
                + ", time='" + getReadTime() + "'"
                + ", temp='" + getTemp() + "'"
                + ", humidity='" + getHumidity() + "'"
                + "}";
    }

}
