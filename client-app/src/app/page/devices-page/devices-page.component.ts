import { Component, OnInit } from '@angular/core';

interface Device {
  id: number;
  name: string;
  pictures: string[];
}

@Component({
  selector: 'app-devices-page',
  templateUrl: './devices-page.component.html',
  styleUrls: ['./devices-page.component.scss']
})
export class DevicesPageComponent implements OnInit {

  constructor() { }

  devices: Device[] = [];

  ngOnInit(): void {
    fetch('/api/v1/example/devices').then(res => res.json()).then(data => {
      this.devices = data;
    });
  }

}
