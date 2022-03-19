import { Component, OnInit } from '@angular/core';
import {Device} from "../../../types/device";

@Component({
  selector: 'app-devices-page',
  templateUrl: './devices-page.component.html',
  styleUrls: ['./devices-page.component.scss']
})
export class DevicesPageComponent implements OnInit {

  constructor() {
    fetch('/api/v1/device').then(res => res.json()).then(data => {
      this.devices = data;
    });
  }

  devices?: Device[];

  ngOnInit(): void {

  }

}
