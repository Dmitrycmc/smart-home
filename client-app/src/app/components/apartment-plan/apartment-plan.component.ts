import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DeviceService} from "../../services/device.service";
import {Device} from "../../../types/device";
import {ActionType, ToggleDevice, WebSocketDto} from "../../../types/web-socket-dto";

@Component({
  selector: 'app-apartment-plan',
  templateUrl: './apartment-plan.component.html',
  styleUrls: ['./apartment-plan.component.scss']
})
export class ApartmentPlanComponent implements OnInit {

  devices?: Device[];
  fetching: boolean = false;
  toggleButtonEvent = new EventEmitter<ToggleDevice>();

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
    this.fetching = true;
    this.deviceService.getAll().subscribe(res => {
      this.devices = res;
      this.fetching = false;

      this.deviceService.openWebSocket(this.toggleButtonEvent).subscribe((toggleDevice: ToggleDevice) => {
        const device = this.devices?.find(d => d.id === toggleDevice.id);
        if (!device) {
          // Logger device not found
          return;
        }
        device!.active = toggleDevice.active;
      });
    });
  }


  onClick(id: number): void {
    const device = this.devices?.find(d => d.id === id);
    if (!device) {
      // Logger device not found
      return;
    }

    this.toggleButtonEvent.emit({
      id,
      active: !device.active
    });
  }

  logFlatCoordinates(e: MouseEvent): void {
    const rect = (e.target as HTMLElement).getBoundingClientRect();
    console.log(Math.round(100 * (e.x - rect.x) / rect.width) + '%', Math.round(100 * (e.y - rect.y) / rect.height) + '%');
  }

}
