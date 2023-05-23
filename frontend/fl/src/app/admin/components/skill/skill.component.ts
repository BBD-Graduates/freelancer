import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent {
  data:any;
  constructor(private _httpClient:HttpClient){
    this.getData();
  }
  getData(){
    this._httpClient.get(config.skillApi.getSkills).subscribe(res=>{  
      this.data = res;
    });
  }
}
