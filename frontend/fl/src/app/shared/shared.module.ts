import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MobileComponent } from './mobile/mobile.component';
import { EmailComponent } from './email/email.component';
import { SocialLinksComponent } from './social-links/social-links.component';
import { ButtonComponent } from './button/button.component';
import { HeadingComponent } from './heading/heading.component';
import { RatingComponent } from './rating/rating.component';
import { TextboxComponent } from './textbox/textbox.component';
import { ProjectCardComponent } from './project-card/project-card.component';
import { ProjectListComponent } from './project-list/project-list.component';

@NgModule({
  declarations: [
    MobileComponent,
    EmailComponent,
    SocialLinksComponent,
    ButtonComponent,
    HeadingComponent,
    RatingComponent,
    TextboxComponent,
    // GoogleAuthComponent,
    ProjectCardComponent,
    ProjectListComponent,
  ],
  imports: [CommonModule],
  exports: [
    MobileComponent,
    EmailComponent,
    SocialLinksComponent,
    ButtonComponent,
    HeadingComponent,
    RatingComponent,
    TextboxComponent,
    ProjectListComponent
  ],
})
export class SharedModule {}
