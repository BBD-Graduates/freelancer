import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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
import { BrowseFreelancersComponent } from './components/browseFreelancers/browse-freelancers/browse-freelancers.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { UserProfileComponent } from './components/userProfile/user-profile/user-profile.component';
import { PostProjectComponent } from './components/project/post-project/post-project.component';
import { MyProjectsComponent } from './components/myProjects/my-projects/my-projects.component';
import { GoogleAuthComponent } from './components/google-auth/google-auth.component';
import { NgxPaginationModule } from 'ngx-pagination';

import {
  GoogleLoginProvider,
  GoogleSigninButtonModule,
  SocialAuthService,
  SocialAuthServiceConfig,
  SocialLoginModule,
} from '@abacritt/angularx-social-login';
import { FooterComponent } from './components/footer/footer.component';
import { EditUserProfileComponent } from './components/editUserProfile/edit-user-profile/edit-user-profile.component';
import { EditPortfolioComponent } from './components/editPortfolio/edit-portfolio/edit-portfolio.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

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
    BrowseFreelancersComponent,
    LandingPageComponent,
    UserProfileComponent,
    PostProjectComponent,
    MyProjectsComponent,
    GoogleAuthComponent,
    FooterComponent,
    EditUserProfileComponent,
    EditPortfolioComponent,
  ],
  imports: [
    NgxPaginationModule,
    CommonModule,
    UserRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    Ng2SearchPipeModule,
    SocialLoginModule,
    GoogleSigninButtonModule,
  ],
  exports: [ProjectDetailsComponent,NgxPaginationModule],
  providers: [
    SocialAuthService,

    {
      provide: 'SocialAuthServiceConfig',

      useValue: {
        autoLogin: false,

        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,

            provider: new GoogleLoginProvider(
              '213833434245-a09bde18s63cojj5qk9bptmhr20dji8u.apps.googleusercontent.com'
            ),
          },
        ],
      } as SocialAuthServiceConfig,
    },
  ],
})
export class UserModule {}
