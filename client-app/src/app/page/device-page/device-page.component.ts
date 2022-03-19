import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import {Device} from "../../../types/device";
import {DeviceService} from "../../services/device.service";

@Component({
  selector: 'app-device-page',
  templateUrl: './device-page.component.html',
  styleUrls: ['./device-page.component.scss']
})
export class DevicePageComponent implements OnInit {
  deviceId?: number;
  device?: Device;

  constructor(
    private deviceService: DeviceService,
    private activateRoute: ActivatedRoute
  ) {
    this.deviceId = activateRoute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.deviceService.findById(this.deviceId!).subscribe(res => {
      this.device = res;
    });
  }
}
