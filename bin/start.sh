#bin/sh

nohup java -cp /home/pi/SensorDataServer/sensordataserver.jar -Dloader.path="./config" -Dloader.main=com.github.michelb1.sensordataserver.Application org.springframework.boot.loader.PropertiesLauncher &
