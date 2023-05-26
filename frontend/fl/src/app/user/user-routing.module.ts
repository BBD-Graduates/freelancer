import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProjectListComponent } from './components/project/project-list/project-list.component';
import { ProjectDetailsComponent } from './components/project/project-details/project-details.component';
import { BrowseFreelancersComponent } from './components/browseFreelancers/browse-freelancers/browse-freelancers.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { UserProfileComponent } from './components/userProfile/user-profile/user-profile.component';
import { PostProjectComponent } from './components/project/post-project/post-project.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: LandingPageComponent,
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
      {
        path: 'users',
        component: BrowseFreelancersComponent,
      },
      {
        path:'post-project',
        component:PostProjectComponent,
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
