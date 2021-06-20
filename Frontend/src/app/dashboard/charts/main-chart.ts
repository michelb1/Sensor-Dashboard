import Chart from 'chart.js';
import { SensorData } from '../sensor-data';

export class MainChart {

    private canvas : any;
    private ctx;
    public datasets: any;
    public data: any;
    public myChartData;

    private humColor='#1f8ef1';
    private tempColor='#ec250d';
    private gradientStrokeRed;
    private gradientStrokeBlue;

    public initChart(gradient,element){

        var chart_labels = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
        this.datasets = [
        [100, 70, 90, 70, 85, 60, 75, 60, 90, 80, 110, 100],
        [80, 120, 105, 110, 95, 105, 90, 100, 80, 95, 70, 120],
        [60, 80, 65, 130, 80, 105, 90, 130, 70, 115, 60, 130]
        ];
        this.data = this.datasets[0];

        this.canvas = document.getElementById(element);
        this.ctx = this.canvas.getContext("2d");

        this.gradientStrokeRed = this.ctx.createLinearGradient(0, 230, 0, 50);
        this.gradientStrokeBlue = this.ctx.createLinearGradient(0, 230, 0, 50);

        this.gradientStrokeRed.addColorStop(1, 'rgba(233,32,16,0.4)');
        this.gradientStrokeRed.addColorStop(0.4, 'rgba(233,32,16,0.2)');
        this.gradientStrokeRed.addColorStop(0, 'rgba(233,32,16,0)'); //red colors

        this.gradientStrokeBlue.addColorStop(1, 'rgba(29,140,248,0.4)');
        this.gradientStrokeBlue.addColorStop(0.4, 'rgba(29,140,248,0.2)');
        this.gradientStrokeBlue.addColorStop(0, 'rgba(29,140,248,0)'); //blue colors

        var config = {
        type: 'line',
        data: {
            labels: chart_labels,
            datasets: 
                [{
                    label: "temp",
                    fill: true,
                    backgroundColor: this.gradientStrokeRed,
                    borderColor: this.tempColor,
                    borderWidth: 2,
                    borderDash: [],
                    borderDashOffset: 0.0,
                    pointBackgroundColor: this.tempColor,
                    pointBorderColor: 'rgba(255,255,255,0)',
                    pointHoverBackgroundColor: this.tempColor,
                    pointBorderWidth: 20,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 15,
                    pointRadius: 1,
                    data: this.data,
                },
                {
                    label: "humidity",
                    fill: true,
                    backgroundColor: this.gradientStrokeBlue,
                    borderColor: this.humColor,
                    borderWidth: 2,
                    borderDash: [],
                    borderDashOffset: 0.0,
                    pointBackgroundColor: this.humColor,
                    pointBorderColor: 'rgba(255,255,255,0)',
                    pointHoverBackgroundColor: this.humColor,
                    pointBorderWidth: 20,
                    pointHoverRadius: 4,
                    pointHoverBorderWidth: 15,
                    pointRadius: 1,
                    data: this.datasets[1],
                    }
            ]
        },
        options: gradient
        };
        this.myChartData = new Chart(this.ctx, config);
    }

    public setMode(mode){

        switch(mode){
            case 'TEMP': {
                this.myChartData.data.datasets[0].data = this.datasets[0];
                delete this.myChartData.data.datasets[1].data;
                this.setColor(this.tempColor,this.gradientStrokeRed);
                break;
            }
            case 'HUMIDITY': {
                this.myChartData.data.datasets[0].data = this.datasets[1];
                delete this.myChartData.data.datasets[1].data;
                this.setColor(this.humColor,this.gradientStrokeBlue);
                break;
            }
            case 'ALL': {
                this.myChartData.data.datasets[0].data = this.datasets[0];
                this.myChartData.data.datasets[1].data = this.datasets[1];
                break;
            }
            default: {
                break;
            }
        }
        
        this.myChartData.update();
    }

    public setColor(color,gradient){
        this.myChartData.data.datasets[0].borderColor=color;
        this.myChartData.data.datasets[0].pointBackgroundColor=color;
        this.myChartData.data.datasets[0].pointHoverBackgroundColor=color;
        this.myChartData.data.datasets[0].backgroundColor=gradient;
    }

    public setData(data:SensorData){
        this.datasets[0]=data.temperature;
        this.datasets[1]=data.humidity;
        this.myChartData.data.datasets[0].data = data.temperature;
        this.myChartData.data.datasets[1].data = data.humidity;
        this.myChartData.data.labels=data.datetime;

        this.myChartData.update();
    }

    public addData(data:SensorData){
        this.datasets[0].push(data.temperature[0]);
        this.datasets[1].push(data.humidity[0]);
        this.myChartData.data.datasets[0].data.push(data.temperature[0]);
        this.myChartData.data.datasets[1].data.push(data.humidity[0]);
        this.myChartData.data.labels.push(data.datetime[0]);
        this.myChartData.update();
    }
}
