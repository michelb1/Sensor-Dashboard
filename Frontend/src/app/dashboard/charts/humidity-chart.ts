import Chart from 'chart.js';
import { SensorData } from '../sensor-data';

export class HumidityChart {

    private canvas : any;
    private ctx;
    private myChartData: Chart;
    private humColor='#1f8ef1';

    public initChart(gradient,element){
      this.canvas = document.getElementById(element);
      this.ctx = this.canvas.getContext("2d");

      var gradientStroke = this.ctx.createLinearGradient(0, 230, 0, 50);
      gradientStroke.addColorStop(1, 'rgba(29,140,248,0.4)');
      gradientStroke.addColorStop(0.4, 'rgba(29,140,248,0.2)');
      gradientStroke.addColorStop(0, 'rgba(29,140,248,0)'); //blue colors

      var data = {
      labels: ['JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'],
      datasets: [{
          label: "Data",
          fill: true,
          backgroundColor: gradientStroke,
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
          data: [80, 100, 70, 80, 120, 80],
      }]
      };

      this.myChartData = new Chart(this.ctx, {
      type: 'line',
      data: data,
      options: gradient
      });
  }

    public setData(data:SensorData){
      
      this.myChartData.data.datasets[0].data = data.humidity;
      this.myChartData.data.labels=data.datetime;
      this.myChartData.update();
    }

    public addData(data:SensorData){
      console.log('addData');
      this.myChartData.data.datasets[0].data.shift();
      this.myChartData.data.labels.shift();
      console.log(this.myChartData.data.labels);
      this.myChartData.update();
      this.myChartData.data.datasets[0].data.push(data.humidity[0]);
      this.myChartData.data.labels.push(data.datetime[0]);
      console.log(this.myChartData.data.labels);
      this.myChartData.update();
    }
}
