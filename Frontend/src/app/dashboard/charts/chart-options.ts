export class ChartOtions {

  public getChartConfigurationTime(){

    var optionsTimeFormat: any = {
        maintainAspectRatio: false,
        legend: {
          display: false
        },
  
        tooltips: {
          backgroundColor: '#f5f5f5',
          titleFontColor: '#333',
          bodyFontColor: '#666',
          bodySpacing: 4,
          xPadding: 12,
          mode: "nearest",
          intersect: 0,
          position: "nearest"
        },
        responsive: true,
        scales: {
          yAxes: [{
            barPercentage: 1.6,
            gridLines: {
              drawBorder: false,
              color: 'rgba(29,140,248,0.0)',
              zeroLineColor: "transparent",
            },
            ticks: {
              padding: 1,
              fontColor: "#9e9e9e"
            }
          }],
  
          xAxes: [{
            type: "time",
            time: {
                format: "HH:mm:ss",
                tooltipFormat: "ll HH:mm",
            },
            scaleLabel: {
                display: true,
                labelString: "Date",
            },
            barPercentage: 1.6,
            gridLines: {
              drawBorder: false,
              color: 'rgba(0,242,195,0.1)',
              zeroLineColor: "transparent",
            },
            ticks: {
              padding: 20,
              fontColor: "#9e9e9e"
            }
          }]
        }
      };
    return optionsTimeFormat;
  }

    public getChartConfiguration1(){
        
        var gradientBarChartConfiguration: any = {
            maintainAspectRatio: false,
            legend: {
              display: false
            },
      
            tooltips: {
              backgroundColor: '#f5f5f5',
              titleFontColor: '#333',
              bodyFontColor: '#666',
              bodySpacing: 4,
              xPadding: 12,
              mode: "nearest",
              intersect: 0,
              position: "nearest"
            },
            responsive: true,
            scales: {
              yAxes: [{
      
                gridLines: {
                  drawBorder: false,
                  color: 'rgba(29,140,248,0.1)',
                  zeroLineColor: "transparent",
                },
                ticks: {
                  suggestedMin: 60,
                  suggestedMax: 120,
                  padding: 20,
                  fontColor: "#9e9e9e"
                }
              }],
      
              xAxes: [{
      
                gridLines: {
                  drawBorder: false,
                  color: 'rgba(29,140,248,0.1)',
                  zeroLineColor: "transparent",
                },
                ticks: {
                  padding: 20,
                  fontColor: "#9e9e9e"
                }
              }]
            }
          };
        return gradientBarChartConfiguration;
    }

    public getChartConfiguration2(){

        var gradientChartOptionsConfigurationWithTooltipGreen: any = {
            maintainAspectRatio: false,
            legend: {
              display: false
            },
      
            tooltips: {
              backgroundColor: '#f5f5f5',
              titleFontColor: '#333',
              bodyFontColor: '#666',
              bodySpacing: 4,
              xPadding: 12,
              mode: "nearest",
              intersect: 0,
              position: "nearest"
            },
            responsive: true,
            scales: {
              yAxes: [{
                barPercentage: 1.6,
                gridLines: {
                  drawBorder: false,
                  color: 'rgba(29,140,248,0.0)',
                  zeroLineColor: "transparent",
                },
                ticks: {
                  suggestedMin: 50,
                  suggestedMax: 125,
                  padding: 20,
                  fontColor: "#9e9e9e"
                }
              }],
      
              xAxes: [{
                barPercentage: 1.6,
                gridLines: {
                  drawBorder: false,
                  color: 'rgba(0,242,195,0.1)',
                  zeroLineColor: "transparent",
                },
                ticks: {
                  padding: 20,
                  fontColor: "#9e9e9e"
                }
              }]
            }
          };
        return gradientChartOptionsConfigurationWithTooltipGreen;
    }

}
