#bin/sh

PiIP=192.168.178.21

ssh pi@$PiIP -t "~/SensorDataServer/stop.sh"
scp ../target/sensordataserver.jar pi@$PiIP:~/SensorDataServer/
ssh pi@$PiIP -t "~/SensorDataServer/start.sh"
