import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'fl-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit{

  data:any=[];
  alert:boolean=false
  insertBid = new FormGroup({
    projectId : new FormControl(this.route.snapshot.paramMap.get('id')),
    freelancerId : new FormControl('',Validators.required),
    amount : new FormControl('',Validators.required),
    description : new FormControl('',Validators.required),
    deliveryDays : new FormControl('',Validators.required),

})
  locationData:any=[];
  http: any;
  constructor (private _httpClient:HttpClient,private route:ActivatedRoute){}
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this._httpClient.get(config.projectApi.getProjectByProjectId+id).subscribe((response: any)=>{
      this.data=response;
      console.log(this.data);
  })
  const pid = this.route.snapshot.paramMap.get('id');
  this._httpClient.get(config.UserApi.getLocation).subscribe((response:any)=>{
    this.locationData = response;
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
