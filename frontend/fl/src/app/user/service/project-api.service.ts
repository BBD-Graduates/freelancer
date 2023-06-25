import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { ProjectResponse } from 'src/app/shared/model/projectResponse';
import { ProjectModel } from 'src/app/shared/model/projectModel';
import { Router } from '@angular/router';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';

@Injectable({
  providedIn: 'root',
})
export class ProjectApiService {
  constructor(private http: HttpClient, private router: Router) { }
  data: any = [];
  async getProjects({
    projectId,
    skillIds,
    clientId,
    freelancerId,
    status,
    categoryId,
  }: {
    projectId?: number;
    skillIds?: number[];
    clientId?: number;
    freelancerId?: number;
    status?: String[];
    categoryId?: number;
  }): Promise<ProjectResponse[] | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'projectId', projectId);
      params = this.addParamsIfNotEmpty(params, 'skillIds', skillIds);
      params = this.addParamsIfNotEmpty(params, 'clientId', clientId);
      params = this.addParamsIfNotEmpty(params, 'freelancerId', freelancerId);
      params = this.addParamsIfNotEmpty(params, 'status', status);
      params = this.addParamsIfNotEmpty(params, 'categoryId', categoryId);
      const options = { params: params };
      console.log("params",params);

      const projectResponse = await this.http
        .get(config.projectApi.getProjects, options)
        .toPromise();


      const data = projectResponse as ApiResponse;
      if (data.response != null) {
        const projects = data.response as ProjectResponse[];
        return projects;
      } else {
        return null;
      }
    } catch (error) {
      console.error('GetProjectsApi_ERROR', error);
      return null;
    }
  }

  postProject(data: ProjectModel) {
    return this.http.post(config.projectApi.insertProject, data);
  }
  addParamsIfNotEmpty(params: any, key: string, value: any): any {
    if (value !== null && value !== undefined && value !== '') {
      params = params.set(key, value);
    }
    return params;
  }
  async updateProject({ projectId, projectStatus, }: {
    projectId?: number;
    projectStatus?: String;
  }): Promise<String | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'projectId', projectId);
      params = this.addParamsIfNotEmpty(params, 'projectStatus', projectStatus);
      const options = { params: params };

      const projectApiResponse = await this.http.put(config.projectApi.getProjects, {},options).toPromise();
      const data = projectApiResponse as ApiResponse;
      if (data.response != null) {
        const projects = data.response as String;
        return projects;
      } else {
        return null;
      }
    }
    catch (error) {
      console.log('update project error', error)
      return null;
    }
  }
}
