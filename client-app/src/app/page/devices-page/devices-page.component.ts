import { Component, OnInit } from '@angular/core';
import {DeviceService} from "../../services/device.service";
import {Device} from "../../../types/device";

@Component({
  selector: 'app-devices-page',
  templateUrl: './devices-page.component.html',
  styleUrls: ['./devices-page.component.scss']
})
export class DevicesPageComponent implements OnInit {

  devices?: Device[];

  constructor(private deviceService: DeviceService) {}

  ngOnInit(): void {
    this.deviceService.findAll().subscribe(res => {
      this.devices = res;
    });
  }

}
