import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { categoryResponse } from 'src/app/shared/model/categoryResponse';
import { skillResponse } from 'src/app/shared/model/skillResponse';
import { userSkillModel } from 'src/app/shared/model/userSkill';

@Injectable({
  providedIn: 'root',
})
export class SkillApiService {
  constructor(private http: HttpClient, private router: Router) { }

  async getSkill(): Promise<skillResponse[] | null> {
    try {
      const skillResponse = await this.http
        .get(config.skillApi.getSkills)
        .toPromise();

      const data = skillResponse as ApiResponse;
      if (data.response != null) {
        const skills = data.response as skillResponse[];
        return skills;
      } else {
        return null;
      }
    } catch (error) {
      console.error('getSkill_ERROR', error);
      return null;
    }
  }
  async getCategory({ categoryId }: { categoryId?: number }): Promise<categoryResponse[] | null> {
    try {
      let params = new HttpParams();
      params = this.addParamsIfNotEmpty(params, 'categoryId', categoryId);
      const options = { params: params };
      const categoryResponse = await this.http
        .get(config.skillApi.getCategorySkills, options)
        .toPromise();

      const data = categoryResponse as ApiResponse;
      if (data.response != null) {
        const categories = data.response as categoryResponse[];
        return categories;
      } else {
        return null;
      }
    } catch (error) {
      console.error('getCategory_ERROR', error);
      return null;
    }
  }
  async adduserSkill(userSkill: userSkillModel[]) {
    try {
      return await this.http.post(config.skillApi.userSkills, userSkill);
    }
    catch (error) {
      console.log('adduserSkill_ERROR', error)
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
