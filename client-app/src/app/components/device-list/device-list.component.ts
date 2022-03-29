import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {Device} from "../../../types/device";
import {Page} from "../../../types/page";
import {DeviceService} from "../../services/device.service";
import {debounce} from "lodash";

@Component({
  selector: 'app-device-list',
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.scss']
})
export class DeviceListComponent implements OnInit {

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
    this.deviceService.search(this.nameFilter, this.currentPage, 6).subscribe(res => {
      this.devices = res;
      this.fetching = false;
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
