import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProjectListComponent } from './components/project/project-list/project-list.component';
import { ProjectDetailsComponent } from './components/project/project-details/project-details.component';

const routes: Routes = [
  // { path: 'home', component: HomeComponent },
  {
    path: 'home', component: HomeComponent,
    children: [
      {
        path: '',
        component: DashboardComponent,
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'skill',
        component: ProjectListComponent,
      },
      {
        path: 'skill/:id',
        component: ProjectListComponent,
      },
      {
        path: 'skill/:id/project-details',
        component: ProjectDetailsComponent,
      },
      {
        path: 'skill/:id/project-details/:id',
        component: ProjectDetailsComponent,
      },
    ]
  },
  // { path: 'dashboard', component: DashboardComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
