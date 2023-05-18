import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit{
  data:any=[];
  constructor(private _httpClient:HttpClient,private route:ActivatedRoute){}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    console.log("ProjectDetailsComponent",id);
    this._httpClient.get(config.projectApi.getProject+id).subscribe((response: any)=>{
      this.data=response;
      console.warn(this.data);
  })
}
getProject(id :number)
{
  return id;
}
}
