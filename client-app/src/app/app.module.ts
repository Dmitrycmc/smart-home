import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DevicesPageComponent } from './page/devices-page/devices-page.component';
import { DevicePageComponent } from './page/device-page/device-page.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { PaginationComponent } from './components/pagination/pagination.component';
import { ApartmentPlanComponent } from './components/apartment-plan/apartment-plan.component';
import {MatTooltipModule} from "@angular/material/tooltip";
import { DeviceListComponent } from './components/device-list/device-list.component';

@NgModule({
  declarations: [
    AppComponent,
    DevicesPageComponent,
    DevicePageComponent,
    PaginationComponent,
    ApartmentPlanComponent,
    DeviceListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
