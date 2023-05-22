import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UserModel } from 'src/app/shared/model/userModel';
import { ApiResponse } from 'src/app/shared/model/apiResponse';

@Injectable({
  providedIn: 'root',
})
export class UserapiService {
  constructor(private http: HttpClient, private router: Router) {}
  baseUrl = 'http://localhost:8081/';
  userUrl = 'users';

  async loginUser(userData: UserModel) {
    try {
      console.log('loginUser');
      const userStatus = await this.getAllUsers({ email: userData.email });
      if (userStatus?.response.length > 0) {
        console.log('User Exist');
        console.log('authState user: ', userData);
        sessionStorage.setItem('userEmail', userStatus?.response[0]['email']);
        if (userStatus?.response[0]['userRole'] == 'Admin') {
        } else {
          this.router.navigate(['/home/dashboard']).then(() => {
            window.location.reload();
          });
        }
      } else {
        if (userData.email != null) {
          console.log('User not Exist');
          const newUser = await this.registerUser(userData);
          if (newUser?.message == 'Registration successful') {
            console.log('New User Registered');
            sessionStorage.setItem('userEmail', userData.email);
            this.router.navigate(['/home/dashboard']).then(() => {
              window.location.reload();
            });
          }
        }
      }
    } catch (error) {
      console.log(error);
    }
  }

  async getAllUsers({
    languageId,
    userId,
    skillId,
    countryId,
    email,
  }: {
    languageId?: number;
    userId?: number;
    skillId?: number;
    countryId?: number;
    email?: String;
  }) {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'languageId', languageId);
      params = this.addParamsIfNotEmpty(params, 'userId', userId);
      params = this.addParamsIfNotEmpty(params, 'skillId', skillId);
      params = this.addParamsIfNotEmpty(params, 'countryId', countryId);
      params = this.addParamsIfNotEmpty(params, 'email', email);
      const options = { params: params };
      const userResponse = await this.http
        .get(this.baseUrl + this.userUrl, options)
        .toPromise();

      const response = userResponse as ApiResponse;
      return response;
    } catch (error) {
      console.error('GetAllUsersApi_ERROR', error);
      return null;
    }
  }

  async registerUser(userData: UserModel) {
    try {
      const userResponse = await this.http
        .post(this.baseUrl + this.userUrl, userData)
        .toPromise();
      const response = userResponse as ApiResponse;
      return response;
    } catch (error) {
      console.error('RegisterUserApi_ERROR', error);
      return null;
    }
  }

  addParamsIfNotEmpty(params: any, key: string, value: any): any {
    if (value !== null && value !== undefined && value !== '') {
      params = params.set(key, value);
    }
    return params;
  }
}
