import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './home/home.component';
import { OpenProjectsComponent } from './components/open-projects/open-projects.component';
import { PastProjectsComponent } from './components/past-projects/past-projects.component';


@NgModule({
  declarations: [
    HomeComponent,
    OpenProjectsComponent,
    PastProjectsComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule
  ],
  exports:[
    HomeComponent
  ]
})
export class ClientModule { }
