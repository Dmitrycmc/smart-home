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

  public findAll(nameFilter?: string, page?: number, size?: number): Observable<Page<Device>> {
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
    return this.http.get<Page<Device>>('/api/v1/device', {params});
  }

  public findById(id: number): Observable<Device> {
    return this.http.get<Device>(`/api/v1/device/${id}`);
  }
}
