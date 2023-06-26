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
import { MyProjectsComponent } from './components/myProjects/my-projects/my-projects.component';
import { AuthGuardService } from './service/auth-gaurd.service';
import { EditUserProfileComponent } from './components/editUserProfile/edit-user-profile/edit-user-profile.component';
import { EditPortfolioComponent } from './components/editPortfolio/edit-portfolio/edit-portfolio.component';

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
        canActivate: [AuthGuardService],
      },
      {
        path: 'category/:categoryid',
        component: ProjectListComponent,
      },
      {
        path: 'skill/:skillId',
        component: ProjectListComponent,
        pathMatch: 'prefix',
      },
      {
        path: 'skill/:id/project-details',
        component: ProjectDetailsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'project-list',
        component: ProjectListComponent,
      },
      {
        path: 'category/:categoryid/project-details/:projectId',
        component: ProjectDetailsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'skill/:id/project-details/:projectId',
        component: ProjectDetailsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'project-details/:projectId',
        component: ProjectDetailsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'project-list/project-details/:projectId',
        component: ProjectDetailsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'users',
        component: BrowseFreelancersComponent,
      },
      {
        path: 'editProfile',
        component: EditUserProfileComponent,
      },
      {
        path: 'editportfolio',
        component: EditPortfolioComponent,
      },
      {
        path: 'myProjects',
        component: MyProjectsComponent,
        canActivate: [AuthGuardService],
        children: [
          {
            path: 'client',
            loadChildren: () =>
              import('./module/client/client.module').then(
                (m) => m.ClientModule
              ),
          },
          {
            path: 'freelancer',
            loadChildren: () =>
              import('./module/freelancer/freelancer.module').then(
                (m) => m.FreelancerModule
              ),
          },
          {
            path: '',
            redirectTo: 'client',
            pathMatch: 'full',
          },
        ],
      },
      {
        path: 'users/:userId',
        component: UserProfileComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'post-project',
        component: PostProjectComponent,
        canActivate: [AuthGuardService],
      },
    ],

  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
