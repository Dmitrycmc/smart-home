import {Component, OnInit} from '@angular/core';
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

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
    this.fetching = true;
    this.deviceService.getAll().subscribe(res => {
      this.devices = res;
      this.fetching = false;

      this.deviceService.openWebSocket().subscribe(str => {
        const webSocketDto = JSON.parse(str) as WebSocketDto;
        if (webSocketDto.actionType !== ActionType.TOGGLE_DEVICE) {
          return;
        }
        const toggleDevice = webSocketDto as ToggleDevice;
        const toggledDevice = this.devices?.find(d => d.id === toggleDevice.id);
        if (!toggleDevice) {
          // Logger device not found
          return;
        }
        toggledDevice!.active = toggleDevice.active;
      });
    });
  }

  onClick(id: number): void {
    this.deviceService.toggle(id).subscribe();
  }

  a(e: MouseEvent): void {
    const rect = (e.target as HTMLElement).getBoundingClientRect();
    console.log(Math.round(100 * (e.x - rect.x) / rect.width) + '%', Math.round(100 * (e.y - rect.y) / rect.height) + '%');
  }

}
