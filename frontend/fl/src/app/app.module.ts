import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import { MenuComponent } from './menu/menu.component';
import { MatMenuModule } from '@angular/material/menu';
import { HomeComponent } from './home/home.component';
import { AuthLinksComponent } from './shared/auth-links/auth-links.component';
import { MobileComponent } from './shared/mobile/mobile.component';
import { EmailComponent } from './shared/email/email.component';
import { JobLinksComponent } from './shared/job-links/job-links.component';
import { SocialLinksComponent } from './shared/social-links/social-links.component';
import { ButtonComponent } from './shared/button/button.component';
import { RatingComponent } from './shared/raitng/rating.component';
import { TestComponent } from './shared/test/test.component';
import { CardComponent } from './shared/card/card.component';
import { CardFooterComponent } from './shared/card-footer/card-footer.component';
import { HeroComponent } from './home/hero/hero.component';
import { UserComponent } from './home/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { HeadingComponent } from './shared/heading/heading.component';
import { HowItWorksComponent } from './user/home/how-it-works/how-it-works.component';
import { SliderComponent } from './shared/slider/slider.component';

import { ProjectListComponent } from './user/project/project-list/project-list.component';
import { ProjectDetailsComponent } from './user/project/project-details/project-details.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
    AuthLinksComponent,
    MobileComponent,
    EmailComponent,
    JobLinksComponent,
    SocialLinksComponent,
    ButtonComponent,
    RatingComponent,
    TestComponent,
    CardComponent,
    CardFooterComponent,
    HeroComponent,
    UserComponent,
    HeadingComponent,
    HowItWorksComponent,
    SliderComponent,
    ProjectListComponent,
    ProjectDetailsComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatDividerModule,
    MatIconModule,
    MatMenuModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
