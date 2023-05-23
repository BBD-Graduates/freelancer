import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { config } from 'src/app/config';

@Component({
  selector: 'fl-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  data:any;
  constructor(private _httpClient:HttpClient){
    this.getData();
  }

  getData(){
    this._httpClient.get(config.skillApi.getCategorySkills).subscribe(res=>{  
      this.data = res
    });

  }
}
