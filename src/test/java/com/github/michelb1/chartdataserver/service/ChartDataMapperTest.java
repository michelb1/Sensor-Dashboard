package com.github.michelb1.chartdataserver.service;

import com.github.michelb1.sensordataserver.service.ChartDataMapper;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import com.github.michelb1.sensordataserver.models.ChartData;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

	
@ActiveProfiles("local")
public class ChartDataMapperTest {

    private ChartDataMapper mapper = new ChartDataMapper();

    private List<TemperatureHumidity> getTestData(){
        List<TemperatureHumidity> list = new ArrayList<TemperatureHumidity>();
        Timestamp time = new Timestamp(new Date().getTime());
        
        for (int i = 0; i < 100; i++) {
            list.add(new TemperatureHumidity(1L, time, "10.5161651651", "20.4234234"));    
        }
        
        return list;
    }

    private ChartData getChartData(int res){
        return mapper.mapEntityToModel(getTestData(), res, false);
    }

    @Test
    public void testNumberOfEntries(){
        ChartData data = getChartData(10);

        assertEquals(10, data.getTemperature().size());
    }

    @Test
    public void testResolution0(){
        ChartData data = getChartData(10);
        BigDecimal dec = new BigDecimal(10.5).setScale(1, RoundingMode.HALF_UP);
        BigDecimal dec2 = new BigDecimal(20.4).setScale(1, RoundingMode.HALF_UP);

        assertEquals(dec, data.getTemperature().get(0));
        assertEquals(dec2, data.getHumidity().get(0));
    }

    @Test
    public void testResolution1(){
        ChartData data = getChartData(5);
        BigDecimal dec = new BigDecimal(10.5).setScale(1, RoundingMode.HALF_UP);
        BigDecimal dec2 = new BigDecimal(20.4).setScale(1, RoundingMode.HALF_UP);

        assertEquals(dec, data.getTemperature().get(1));
        assertEquals(dec2, data.getHumidity().get(1));
    }

    @Test
    public void testResolution2(){
        ChartData data = getChartData(3);
        BigDecimal dec = new BigDecimal(10.5).setScale(1, RoundingMode.HALF_UP);
        BigDecimal dec2 = new BigDecimal(20.4).setScale(1, RoundingMode.HALF_UP);

        assertEquals(dec, data.getTemperature().get(2));
        assertEquals(dec2, data.getHumidity().get(2));
    }

    @Test
    public void testResolution3(){
        ChartData data = getChartData(1);
        BigDecimal dec = new BigDecimal(10.5).setScale(1, RoundingMode.HALF_UP);
        BigDecimal dec2 = new BigDecimal(20.4).setScale(1, RoundingMode.HALF_UP);

        assertEquals(dec, data.getTemperature().get(2));
        assertEquals(dec2, data.getHumidity().get(2));
        assertEquals(100, data.getTemperature().size());
    }

    @Test
    public void testResolution4(){
        ChartData data = getChartData(200);
        BigDecimal dec = new BigDecimal(10.5).setScale(1, RoundingMode.HALF_UP);
        BigDecimal dec2 = new BigDecimal(20.4).setScale(1, RoundingMode.HALF_UP);

        assertEquals(dec, data.getTemperature().get(0));
        assertEquals(dec2, data.getHumidity().get(0));
        assertEquals(1, data.getTemperature().size());
    }
}