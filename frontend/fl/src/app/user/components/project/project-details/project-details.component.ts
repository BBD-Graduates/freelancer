import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ProjectListComponent } from '../project-list/project-list.component';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';



@Component({
  selector: 'fl-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit{

  data:any=[];
  alert:boolean=false
  insertBid = new FormGroup({
    projectId : new FormControl(''),
    freelancerId : new FormControl(''),
    amount : new FormControl(''),
    description : new FormControl(''),
    deliveryDays : new FormControl(''),

})
  http: any;
  constructor (private _httpClient:HttpClient,private route:ActivatedRoute){}
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    console.log("ProjectDetailsComponent",id);
    this._httpClient.get(config.projectApi.getProjectByProjectId+id).subscribe((response: any)=>{
      this.data=response;
      //console.warn("ProjectDetailsComponent",this.data);
  })
}
saveBid(data: any) {
  return this._httpClient.post(config.BidApi.insertBid, data);
}
collectBid() {
  console.log(this.insertBid.value);
  this.saveBid(this.insertBid.value).subscribe((response) => {
    this.alert = true;
    this.insertBid.reset();
  });
}
closeAlert() {
  this.alert = false;
}
}
