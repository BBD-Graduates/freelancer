import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/shared/model/userModel';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { config } from 'src/app/config';

@Injectable({
  providedIn: 'root',
})
export class UserapiService {
  constructor(private http: HttpClient, private router: Router) {}

  async loginUser(userData: UserModel) {
    try {
      const userStatus = await this.getAllUsers({ email: userData.email });
      if (userStatus?.response.length > 0) {
        console.log('User Exist');
        localStorage.setItem('userEmail', userStatus?.response[0]['email']);
        localStorage.setItem('userId', userStatus?.response[0]['userId']);
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
            localStorage.setItem('userEmail', userData.email);
            localStorage.setItem('userId', newUser.response);
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
    languageIds,
    userId,
    skillIds,
    categoryId,
    countryId,
    email,
  }: {
    languageIds?: number[];
    userId?: number;
    skillIds?: number[];
    categoryId?: number;
    countryId?: number;
    email?: string;
  }): Promise<ApiResponse | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'languageId', languageIds);
      params = this.addParamsIfNotEmpty(params, 'userId', userId);
      params = this.addParamsIfNotEmpty(params, 'skillId', skillIds);
      params = this.addParamsIfNotEmpty(params, 'categoryId', categoryId);
      params = this.addParamsIfNotEmpty(params, 'countryId', countryId);
      params = this.addParamsIfNotEmpty(params, 'email', email);
      const options = { params: params };
      const userResponse = await this.http
        .get(config.UserApi.getUser, options)
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
        .post(config.UserApi.postUser, userData)
        .toPromise();
      const response = userResponse as ApiResponse;
      return response;
    } catch (error) {
      console.error('RegisterUserApi_ERROR', error);
      return null;
    }
  }

  async updateUser({userId,userData}:{userId:number,userData: UserModel}) {
    try {
      const userResponse = await this.http
        .post(config.UserApi.postUser+"/"+userId, userData)
        .toPromise();
      const response = userResponse as ApiResponse;
      return response;
    } catch (error) {
      console.error('UpdateApi_ERROR', error);
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
