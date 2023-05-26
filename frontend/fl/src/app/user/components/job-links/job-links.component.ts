import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-job-links',
  templateUrl: './job-links.component.html',
  styleUrls: ['./job-links.component.css']
})
export class JobLinksComponent {
  menuData:any;
  constructor(private _httpClient:HttpClient){}

  ngOnInit(): void {
    this._httpClient.get(config.skillApi.getCategorySkills).subscribe(res=>{
      this.menuData = res;
    });
  }
}
