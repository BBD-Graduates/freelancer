import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './home/home.component';
import { OpenProjectsComponent } from './components/open-projects/open-projects.component';
import { PastProjectsComponent } from './components/past-projects/past-projects.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProjectDetailComponent } from './components/project-detail/project-detail.component';
import { UserBidsComponent } from './components/user-bids/user-bids.component';
import { WorkInProgressComponent } from './components/workInProgress/work-in-progress/work-in-progress.component';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    HomeComponent,
    OpenProjectsComponent,
    PastProjectsComponent,
    ProjectDetailComponent,
    UserBidsComponent,
    WorkInProgressComponent,
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    SharedModule,
    NgxPaginationModule,
  ],
  exports:[
    HomeComponent
  ]
})
export class ClientModule { }
