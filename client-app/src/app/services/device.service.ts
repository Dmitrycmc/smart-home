import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Device} from "../../types/device";
import {Page} from "../../types/page";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(private http: HttpClient) { }

  public getAll(): Observable<Device[]> {
    return this.http.get<Device[]>('/api/v1/device');
  }

  public search(nameFilter?: string, page?: number, size?: number): Observable<Page<Device>> {
    let params = new HttpParams();
    if (nameFilter) {
      params = params.set('nameFilter', nameFilter);
    }
    if (page) {
      params = params.set('page', page);
    }
    if (size) {
      params = params.set('size', size);
    }
    return this.http.get<Page<Device>>('/api/v1/device/search', {params});
  }

  public findById(id: number): Observable<Device> {
    return this.http.get<Device>(`/api/v1/device/${id}`);
  }

  public openWebSocket(): Observable<string> {
    const ws = new WebSocket("ws://localhost:4200/api/web-socket");
    ws.onopen = () => {
      // @ts-ignore
      window.send = m => {ws.send(m);};
    };
    return new Observable<string>(subscriber => {
      ws.onmessage = (m) => {
        subscriber.next(m.data);
      };
    });
  }

  toggle(id: number): Observable<void> {
    return this.http.post<void>(`/api/v1/device/${id}/toggle`, null);
  }
}
