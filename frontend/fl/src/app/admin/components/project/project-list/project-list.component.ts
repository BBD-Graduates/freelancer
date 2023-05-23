import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent {
  projects:any;
  constructor(private _httpClient:HttpClient){
    this.getData();
  }
  getData(){
    console.log(config.projectApi.getProject)
    this._httpClient.get(config.projectApi.getProject).subscribe(res=>{  
      this.projects = res;
      console.log(this.projects);
    });
  }
}
