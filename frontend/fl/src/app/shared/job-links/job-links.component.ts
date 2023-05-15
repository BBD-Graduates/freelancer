import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-job-links',
  templateUrl: './job-links.component.html',
  styleUrls: ['./job-links.component.scss']
})
export class JobLinksComponent implements OnInit{
  menuData:any;
  constructor(private _httpClient:HttpClient){}

  ngOnInit(): void {
    this._httpClient.get(config.skillApi.getCategorySkills).subscribe(res=>{
      this.menuData = res;
      console.log(this.menuData);
    });
  }
}
