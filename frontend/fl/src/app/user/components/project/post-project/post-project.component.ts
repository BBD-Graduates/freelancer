import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'fl-post-project',
  templateUrl: './post-project.component.html',
  styleUrls: ['./post-project.component.css']
})
export class PostProjectComponent implements OnInit{

  projectdata:any=[];
  alert:boolean=false
  insertProject = new FormGroup({
    projectName : new FormControl('',Validators.required),
    projectDescription : new FormControl('',Validators.required),
    isConfidential : new FormControl('',Validators.required),
    bidStartDate : new FormControl('',Validators.required),
    bidEndDate : new FormControl('',Validators.required),
    minPrice : new FormControl('',Validators.required),
    maxPrice : new FormControl('',Validators.required),
    status : new FormControl('',Validators.required)

})
constructor (private _httpClient:HttpClient,private route:ActivatedRoute){}
  ngOnInit(): void {

  }
  saveProject(projectdata: any) {
    return this._httpClient.post(config.projectApi.insertProject,projectdata);
  }
  collectProject() {
    console.log(this.insertProject.value);
    this.saveProject(this.insertProject.value).subscribe((response) => {
      this.alert = true;
      this.insertProject.reset();
    });
  }
  closeAlert() {
    this.alert = false;
  }
}
