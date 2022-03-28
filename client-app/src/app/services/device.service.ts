import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import { webSocket } from "rxjs/webSocket";
import {Device} from "../../types/device";
import {Page} from "../../types/page";
import {ActionType, ToggleDevice, WebSocketDto} from "../../types/web-socket-dto";

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

  public openWebSocket(toggleButtonEvent: Observable<ToggleDevice>): Observable<ToggleDevice> {
    const ws = webSocket("ws://localhost:4200/api/web-socket");
    toggleButtonEvent.subscribe(({id, active}) => {

      // todo: reconnect

      ws.next({
        actionType: ActionType.TOGGLE_DEVICE,
        id,
        active
      });
    });

    return new Observable<ToggleDevice>(subscriber => {
      ws.subscribe(msg => {
        const webSocketDto = msg as WebSocketDto;
        if (webSocketDto.actionType !== ActionType.TOGGLE_DEVICE) {
          return;
        }
        return subscriber.next(msg as ToggleDevice)
      });
    });
  }

  toggle(id: number): Observable<void> {
    return this.http.post<void>(`/api/v1/device/${id}/toggle`, null);
  }
}
