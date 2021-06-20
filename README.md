<h1>Sensor Dashboard (Temperature / Humidity)</h1>
Sensor: DHT22<br>
<img width="997" alt="sensordashboard" src="https://user-images.githubusercontent.com/14015916/122676892-41b20f80-d1e0-11eb-8bd1-4453e47ee60b.png">

<h2>local test:</h2>
../Local/json-server .\sensor-local.json<br>
mvn spring-boot:run<br>

<h2>build:</h2>
mvn clean install<br>

<h2>run on Raspberry Pi:</h2>
install SQLite3 -> schema.sql, data.sql<br>
<br>
install DHT Library:<br>
git clone https://github.com/adafruit/Adafruit_Python_DHT.git<br>
cd Adafruit_Python_DHT/<br>
python setup.py install<br>
<br>
Sensor Service: python startSensorService.py<br>
Server: bin/start.sh<br>

<h2>Config:</h2>
Database: persistence.properties<br>
Server: server.properties<br>
SensorService: service.properties

<h2>Insert Sensordata: (when service is disabled)</h2>
curl --header "Content-Type: application/json" --request POST --data '{"temperature":35,"humidity":50}' --user sensor:sensor http://localhost:8080/insert<br>
