import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DevicesPageComponent } from './page/devices-page/devices-page.component';
import { DevicePageComponent } from './page/device-page/device-page.component';

@NgModule({
  declarations: [
    AppComponent,
    DevicesPageComponent,
    DevicePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
