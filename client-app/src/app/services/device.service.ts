import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Device} from "../../types/device";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Device[]> {
    return this.http.get<Device[]>('/api/v1/device');
  }

  public findById(id: number): Observable<Device> {
    return this.http.get<Device>(`/api/v1/device/${id}`);
  }
}
