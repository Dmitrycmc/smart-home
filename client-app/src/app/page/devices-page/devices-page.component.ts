import {Component, OnInit} from '@angular/core';
import {DeviceService} from "../../services/device.service";
import {Device} from "../../../types/device";
import debounce from 'lodash/debounce';
import range from 'lodash/range';
import {Page} from "../../../types/page";

@Component({
  selector: 'app-devices-page',
  templateUrl: './devices-page.component.html',
  styleUrls: ['./devices-page.component.scss']
})
export class DevicesPageComponent implements OnInit {

  devices?: Page<Device>;

  isNameFilterVisible: boolean = false;
  nameFilter?: string;

  currentPage = 0;

  constructor(private deviceService: DeviceService) {}

  showNameFilter() {
    this.isNameFilterVisible = true;
  }

  update() {
    this.deviceService.findAll(this.nameFilter, this.currentPage, 10).subscribe(res => {
      this.devices = res;
    });
  }

  onFilterChange = debounce(() => {
    this.currentPage = 0;
    this.update();
  }, 400);

  setPage(page: number) {
    if (page !== this.currentPage) {
      this.currentPage = page;
      this.update();
    }
  }

  ngOnInit(): void {
    this.update();
  }

}
