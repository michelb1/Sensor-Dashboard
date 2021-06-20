import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from "rxjs/Rx";

@Injectable({
  providedIn: 'root'
})

export class SensorDataService {

  constructor(private http: HttpClient) {

/*
    this.messages = <Subject<SensorData>>this.connect().map(
      (response: MessageEvent): SensorData => {
        let data = JSON.parse(response.data);
        return {
          datetime: data.datetime,
          temperature: data.temperature,
          humidity: data.humidity
        };
      }
    );
*/
  }

  public createWebsocket() {
    var ws = new WebSocket('ws://' + location.host + '/ws');

    return new Observable(
      observer => {
       ws.onmessage = (event) =>
         observer.next(event.data);
       ws.onerror = (event) => observer.error(event);
       ws.onclose = (event) => observer.complete();

       return () =>
           ws.close(1000, "The user disconnected");
      }
   );
  }

  public getChartData() {
    return this.http.get(this.getHost()+'/data');
    //return this.http.get('http://localhost:3000/data');
  }

  public getLatestChartData(limit:string) {
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    let params = new HttpParams().set("limit",limit);

    return this.http.get(this.getHost()+'/data-latest',{headers: headers, params: params});
  }

  public getHost(){
    return location.protocol + '//' + location.host;
  }
}
