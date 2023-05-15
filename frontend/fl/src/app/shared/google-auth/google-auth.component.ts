import { Component } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Router } from '@angular/router';

import {
  GoogleLoginProvider,
  SocialAuthService,
} from '@abacritt/angularx-social-login';
import { UserModel } from 'src/app/model/userModel';
import { UserapiService } from 'src/app/service/userapi.service';

@Component({
  selector: 'fl-google-auth',
  templateUrl: './google-auth.component.html',
  styleUrls: ['./google-auth.component.scss'],
})
export class GoogleAuthComponent {
  constructor(
    private userapiService: UserapiService,
    private router: Router,
    private readonly authService: SocialAuthService,
    private httpClient: HttpClient
  ) {}

  ngOnInit() {
    this.authService.authState.subscribe(async (user: any) => {
      this.getAccessToken();

      let userData: UserModel = {
        firstName: user['firstName'],
        lastName: user['lastName'],
        email: user['email'],
      };

      await this.userapiService.loginUser(userData);

      console.log('authState user: ', user);
      console.log('Token from state: ', user.authToken);
    });
  }

  private accessToken = '';

  user: any;

  googleClientId =
    '213833434245-a09bde18s63cojj5qk9bptmhr20dji8u.apps.googleusercontent.com';

  getAccessToken(): void {
    console.log('getAccessToken');
    this.authService
      .getAccessToken(GoogleLoginProvider.PROVIDER_ID)
      .then((accessToken) => (this.accessToken = accessToken));
  }
}
