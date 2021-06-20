package com.github.michelb1.sensordataserver.repository;

import com.github.michelb1.sensordataserver.entities.TemperatureHumidity;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureHumidityRepository extends CrudRepository<TemperatureHumidity, Long> {

    public List<TemperatureHumidity> findAllByOrderByIdAsc();

    public List<TemperatureHumidity> findByReadTimeGreaterThanOrderByIdAsc(Timestamp time);

    public List<TemperatureHumidity> findTop30ByOrderByIdDesc();
}
