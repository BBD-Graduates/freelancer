import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserRoutingModule } from './user-routing.module';
import { SharedModule } from '../shared/shared.module';
import { HomeComponent } from './home/home.component';
import { MenubarComponent } from './components/menubar/menubar.component';
import { LoginLinksComponent } from './components/login-links/login-links.component';
import { JobLinksComponent } from './components/job-links/job-links.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserCardComponent } from './components/user-card/user-card.component';
import { ProjectDetailsComponent } from './components/project/project-details/project-details.component';
import { ProjectListComponent } from './components/project/project-list/project-list.component';


@NgModule({
  declarations: [
    HomeComponent,
    MenubarComponent,
    LoginLinksComponent,
    JobLinksComponent,
    DashboardComponent,
    UserCardComponent,
    ProjectDetailsComponent,
    ProjectListComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
