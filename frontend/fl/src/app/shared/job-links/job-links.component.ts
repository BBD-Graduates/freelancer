import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'fl-job-links',
  templateUrl: './job-links.component.html',
  styleUrls: ['./job-links.component.scss']
})
export class JobLinksComponent implements OnInit{
  menuData:any;
  constructor(private _httpClient:HttpClient){}

  ngOnInit(): void {
    this._httpClient.get('http://localhost:8082/categories?categoryId=0').subscribe(res=>{
      this.menuData = res;
      console.log(this.menuData);
    });
  }
}
