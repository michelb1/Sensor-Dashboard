import { Component, OnInit } from '@angular/core';
import { TempChart } from './charts/temp-chart';
import { HumidityChart } from './charts/humidity-chart';
import { ChartOtions} from './charts/chart-options'
import { MainChart} from './charts/main-chart'
import { SensorDataService} from './sensor-data.service'
import { SensorData } from './sensor-data'

@Component({
  templateUrl: './dashboard.component.html',
  providers: [SensorDataService]
})
export class DashboardComponent implements OnInit {

  public data: SensorData;
  
  public clicked: boolean = true;
  public clicked1: boolean = false;
  public clicked2: boolean = false;

  //Charts
  private tempchart = new TempChart();
  private humiditychart = new HumidityChart();
  private mainchart = new MainChart();
  private option = new ChartOtions();

  private datalimit = '30';

  constructor(private service : SensorDataService) {

    service.getChartData().subscribe((e)=> {
      var data = e as SensorData;
      this.setDataInCharts(data);
    });

    service.getLatestChartData(this.datalimit).subscribe((e)=> {
      var data = e as SensorData;
      this.setDataInLiveCharts(data);
    });

    service.createWebsocket().subscribe(
      message => {
        var data = JSON.parse(String(message)) as SensorData;        
        this.addDataFromWebsocket(data);
      });
  }

  ngOnInit() {

    this.mainchart.initChart(this.option.getChartConfigurationTime(),"TopChart");
    this.tempchart.initChart(this.option.getChartConfigurationTime(),"TemperatureChart");
    this.humiditychart.initChart(this.option.getChartConfigurationTime(),"HumidityChart");
  }
  
  public updateOptions(mode) {
    this.mainchart.setMode(mode);
  }

  private setDataInCharts(data: SensorData) {
    this.mainchart.setData(data);
  }

  private setDataInLiveCharts(data: SensorData) {
    this.tempchart.setData(data);
    this.humiditychart.setData(data);
  }

  private addDataFromWebsocket(data: SensorData) {
    /*
    console.log('add data from ws: ');
    console.log(data);
    this.tempchart.addData(data);
    this.humiditychart.addData(data);
    */
   this.service.getLatestChartData(this.datalimit).subscribe((e)=> {
    var data = e as SensorData;
    this.setDataInLiveCharts(data);
  });
  }

}