import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MobileComponent } from './mobile/mobile.component';
import { EmailComponent } from './email/email.component';
import { SocialLinksComponent } from './social-links/social-links.component';
import { ButtonComponent } from './button/button.component';
import { HeadingComponent } from './heading/heading.component';
import { RatingComponent } from './rating/rating.component';
import { TextboxComponent } from './textbox/textbox.component';
import { GoogleAuthComponent } from './google_auth/google-auth/google-auth.component';
import {
  GoogleLoginProvider,
  GoogleSigninButtonModule,
  SocialAuthService,
  SocialAuthServiceConfig,
  SocialLoginModule,
} from '@abacritt/angularx-social-login';

@NgModule({
  declarations: [
    MobileComponent,
    EmailComponent,
    SocialLinksComponent,
    ButtonComponent,
    HeadingComponent,
    RatingComponent,
    TextboxComponent,
    GoogleAuthComponent,
  ],
  imports: [CommonModule, SocialLoginModule, GoogleSigninButtonModule],
  exports: [
    MobileComponent,
    EmailComponent,
    SocialLinksComponent,
    ButtonComponent,
    HeadingComponent,
    RatingComponent,
    TextboxComponent,
    GoogleAuthComponent,
  ],
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
export class SharedModule {}
