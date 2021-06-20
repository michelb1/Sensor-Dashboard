package com.github.michelb1.sensordataserver.service;

import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import com.github.michelb1.sensordataserver.models.ChartData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ChartDataMapper {

    public ChartData mapEntityToModel(TemperatureHumidity tempHum) {
        
        ChartData chartData = new ChartData();

        List<Timestamp> dateList = chartData.getDatetime();
        List<BigDecimal> tempList = chartData.getTemperature();
        List<BigDecimal> humidityList = chartData.getHumidity();

        dateList.add(tempHum.getReadTime());
        tempList.add(mapStringToBigDecimal(tempHum.getTemp()));
        humidityList.add(mapStringToBigDecimal(tempHum.getHumidity()));
        
        return chartData;
    }

    public ChartData mapEntityToModel(Iterable<TemperatureHumidity> tempHum, int resolution, boolean reverse) {

        ChartData chartData = new ChartData();

        List<Timestamp> dateList = chartData.getDatetime();
        List<BigDecimal> tempList = chartData.getTemperature();
        List<BigDecimal> humidityList = chartData.getHumidity();

        int n = 0;
        BigDecimal temperatureTemp = BigDecimal.ZERO;
        BigDecimal humidityTemp = BigDecimal.ZERO;
        BigDecimal divident = new BigDecimal(resolution - 1);

        for (TemperatureHumidity e : tempHum) {

            if (e.getTemp() != null && e.getHumidity() != null) {
                BigDecimal temperature = mapStringToBigDecimal(e.getTemp());
                BigDecimal humidity = mapStringToBigDecimal(e.getHumidity());

                if (n % resolution == 0) {
                    dateList.add(e.getReadTime());
                    tempList.add(temperatureTemp.equals(BigDecimal.ZERO) ? temperature : temperatureTemp.divide(divident, 1, RoundingMode.HALF_UP));
                    humidityList.add(humidityTemp.equals(BigDecimal.ZERO) ? humidity : humidityTemp.divide(divident, 1, RoundingMode.HALF_UP));
                    temperatureTemp = BigDecimal.ZERO;
                    humidityTemp = BigDecimal.ZERO;
                } else {
                    temperatureTemp = temperatureTemp.add(temperature);
                    humidityTemp = humidityTemp.add(humidity);
                }
                n++;
            }
        }

        if(reverse){
            Collections.reverse(chartData.getDatetime());
            Collections.reverse(chartData.getHumidity());
            Collections.reverse(chartData.getTemperature());
        }

        return chartData;
    }

    private BigDecimal mapStringToBigDecimal(String temp) {
        BigDecimal decimal = new BigDecimal(temp);
        decimal = decimal.setScale(1, RoundingMode.HALF_UP);
        return decimal;
    }

}
