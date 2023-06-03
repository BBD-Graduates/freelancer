import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { skillResponse } from 'src/app/shared/model/skillResponse';

@Injectable({
  providedIn: 'root'
})
export class SkillApiService {

  constructor(private http: HttpClient, private router: Router) { }
  async getSkill(): Promise<skillResponse[] | null> {
    try {
      const skillResponse = await this.http
        .get(config.skillApi.getSkills)
        .toPromise();

      const data = skillResponse as ApiResponse;
      if(data.response!=null){
        const skills = data.response as skillResponse[];
        return skills;
      }
      else{
        return null;
      }
    }
    catch (error) {
      console.error('getSkill_ERROR', error);
      return null;
    }
  }
}
