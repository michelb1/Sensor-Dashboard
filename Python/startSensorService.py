import Adafruit_DHT
import time
from flask import Flask,jsonify

app = Flask(__name__)

sensor = Adafruit_DHT.DHT22
gpio = 4

@app.route("/sensor")
def getSensorData():

    humidity, temp = Adafruit_DHT.read_retry(sensor, gpio)

    tuple = (str(int(round(time.time() * 1000))),str(temp),str(humidity))
    
    return jsonify(
            time=tuple[0],
            temp=tuple[1],
            humidity=tuple[2]
    )

if __name__ == '__main__':
    app.run(debug=False)

