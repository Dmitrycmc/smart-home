import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {DeviceService} from "../../services/device.service";
import {Device} from "../../../types/device";
import debounce from 'lodash/debounce';
import {Page} from "../../../types/page";

@Component({
  selector: 'app-devices-page',
  templateUrl: './devices-page.component.html',
  styleUrls: ['./devices-page.component.scss']
})
export class DevicesPageComponent implements OnInit {

  devices?: Page<Device>;
  fetching: boolean = false;

  isNameFilterVisible: boolean = false;
  nameFilter?: string;
  @ViewChild('nameFilterRef') filterInput?: ElementRef;

  currentPage = 0;

  constructor(private deviceService: DeviceService) {}

  showNameFilter() {
    this.isNameFilterVisible = true;
    this.filterInput?.nativeElement.focus();
    this.filterInput?.nativeElement.select();
  }

  update() {
    this.fetching = true;
    this.deviceService.findAll(this.nameFilter, this.currentPage, 10).subscribe(res => {
      setTimeout(() => {
          this.devices = res;
          this.fetching = false;
      }, 500);
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
