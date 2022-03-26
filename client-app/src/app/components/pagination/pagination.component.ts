import {Component, EventEmitter, Input, OnInit, Output, SimpleChanges} from '@angular/core';
import range from "lodash/range";
import {Page} from "../../../types/page";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnInit {
  @Input() page?: Page<unknown>;
  @Input() disabled?: boolean;
  @Output() setPage = new EventEmitter<number>();

  currentPage: number = 0;
  pages?: number[];

  constructor() {}

  ngOnChanges(changes: SimpleChanges) {
    if ('page' in changes) {
      this.pages = range(this.page?.totalPages!);
      this.currentPage = this.page?.number!;
    }
  }

  ngOnInit(): void {}

  goToPage(page: number) {
    this.currentPage = page;
    this.setPage.emit(this.currentPage);
  }

  prevPage() {
    this.goToPage(this.currentPage - 1);
  }

  nextPage() {
    this.goToPage(this.currentPage + 1);
  }
}
