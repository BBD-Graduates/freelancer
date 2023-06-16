import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { OpenProjectsComponent } from './components/open-projects/open-projects.component';
import { PastProjectsComponent } from './components/past-projects/past-projects.component';
import { ProjectListComponent } from '../../../shared/project-list/project-list.component';
import { ProjectDetailComponent } from './components/project-detail/project-detail.component';
import { WorkInProgressComponent } from './components/workInProgress/work-in-progress/work-in-progress.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: OpenProjectsComponent,
      },
      {
        path: 'project-details/:projectId',
        component: ProjectDetailComponent,
        data: { projectId: 16 },
      },
      {
        path: 'past',
        component: PastProjectsComponent,
      },
      {
        path: 'past/project-details/:projectId',
        component: ProjectDetailComponent,
      },
      {
        path: 'workInProgress/project-details/:projectId',
        component: ProjectDetailComponent,
      },
      {
        path: 'workInProgress',
        component: WorkInProgressComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientRoutingModule {}
