import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';

@Injectable({
  providedIn: 'root',
})
export class ProjectApiService {
  constructor(private http: HttpClient, private router: Router) {}

  async getProject({
    projectId,
    skillId,
    clientId,
    status,
    categoryId,
  }: {
    projectId?: number;
    skillId?: number;
    clientId?: number;
    status?: String[];
    categoryId?: number;
  }): Promise<ApiResponse | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'projectId', projectId);
      params = this.addParamsIfNotEmpty(params, 'skillId', skillId);
      params = this.addParamsIfNotEmpty(params, 'clientId', clientId);
      params = this.addParamsIfNotEmpty(params, 'status', status);
      params = this.addParamsIfNotEmpty(params, 'categoryId', categoryId);
      const options = { params: params };
      const projectResponse = await this.http
        .get(config.projectApi.getProjects, options)
        .toPromise();

      const response = projectResponse as ApiResponse;
      return response;
    } catch (error) {
      console.error('GetProjectsApi_ERROR', error);
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
