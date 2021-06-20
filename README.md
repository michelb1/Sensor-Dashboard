<h1>Sensor Dashboard (Temperature / Humidity)</h1>
Sensor: DHT22<br>

<h2>local test:</h2>
../Local/json-server .\sensor-local.json
mvn spring-boot:run

<h2>build:</h2>
mvn clean install<br>

<h2>run on Raspberry Pi:</h2>

install SQLite3 -> schema.sql, data.sql

install DHT Library:
git clone https://github.com/adafruit/Adafruit_Python_DHT.git
cd Adafruit_Python_DHT/
python setup.py install

Sensor Service: python startSensorService.py
Server: bin/start.sh

<h2>Config:</h2>
Database: persistence.properties<br>
Server: server.properties<br>
SensorService: service.properties

<h2>Insert Sensordata: (when service is disabled)</h2>
curl --header "Content-Type: application/json" --request POST --data '{"temperature":35,"humidity":50}' --user sensor:sensor http://localhost:8080/insert<br>
