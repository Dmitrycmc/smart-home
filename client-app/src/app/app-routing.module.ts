import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DevicesPageComponent} from "./page/devices-page/devices-page.component";
import {DevicePageComponent} from "./page/device-page/device-page.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'devices'},
  {path: 'devices', component: DevicesPageComponent},
  {path: 'device/:id', component: DevicePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
