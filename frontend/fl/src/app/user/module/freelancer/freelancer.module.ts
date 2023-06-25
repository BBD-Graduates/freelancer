import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FreelancerRoutingModule } from './freelancer-routing.module';
import { HomeComponent } from './home/home.component';
import { BidComponent } from './components/bid/bid.component';
import { CurrentProjectComponent } from './components/current-project/current-project.component';
import { CompletedProjectComponent } from './components/completed-project/completed-project.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    HomeComponent,
    BidComponent,
    CurrentProjectComponent,
    CompletedProjectComponent
  ],
  imports: [
    CommonModule,
    FreelancerRoutingModule,NgxPaginationModule,
  ],
  exports:[
    HomeComponent
  ]
})
export class FreelancerModule { }
