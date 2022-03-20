import {Component, OnInit, SimpleChanges} from '@angular/core';
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

  nameFilter?: string;

  currentPage = 0;
  pages?: number[];

  constructor(private deviceService: DeviceService) {}

  update() {
    this.deviceService.findAll(this.nameFilter, this.currentPage, 10).subscribe(res => {
      this.devices = res;
      this.pages = range(res.totalPages);
      console.log(this.pages);
    });
  }

  onFilterChange = debounce(this.update, 400);

  goToPage(page: number) {
    this.currentPage = page;
    this.update();
  }

  prevPage() {
    this.goToPage(this.currentPage - 1);
  }

  nextPage() {
    this.goToPage(this.currentPage + 1);
  }

  ngOnInit(): void {
    this.update();
  }

}
