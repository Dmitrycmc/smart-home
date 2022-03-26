import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import {Device} from "../../../types/device";

@Component({
  selector: 'app-device-page',
  templateUrl: './device-page.component.html',
  styleUrls: ['./device-page.component.scss']
})
export class DevicePageComponent implements OnInit {
  deviceId?: number;
  device?: Device;

  constructor(private activateRoute: ActivatedRoute) {
    this.deviceId = activateRoute.snapshot.params['id'];
    fetch(`/api/v1/device/${this.deviceId}`).then(res => res.json()).then(data => {
      this.device = data;
    });
  }

  ngOnInit(): void {

  }

}
