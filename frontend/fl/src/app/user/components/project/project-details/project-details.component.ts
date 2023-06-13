import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserapiService } from 'src/app/user/service/user-api.service';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import { ProjectResponse } from 'src/app/shared/model/projectResponse';
import { BidApiService } from 'src/app/user/service/bid-api.service';
import { BidStatus } from 'src/app/enums/bidStatusEnums';

@Component({
  selector: 'fl-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css'],
})
export class ProjectDetailsComponent implements OnInit {
  data: ProjectResponse[] | null = null;
  alert: boolean = false;
  projectId: number = 0;
  projectDaysLeft: number = 0;
  bidEndDate: Date | null = null;
  bidStartDate: Date | null = null;
  freelancerId: number = 0;
  isRejected: boolean = false;

  insertBid = new FormGroup({
    projectId: new FormControl(this.route.snapshot.paramMap.get('projectId')),
    freelancerId: new FormControl(
      localStorage.getItem('userId'),
      Validators.required
    ),
    amount: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    deliveryDays: new FormControl('', Validators.required),
  });
  locationData: any = [];
  http: any;
  constructor(
    private _httpClient: HttpClient,
    private route: ActivatedRoute,
    private userService: UserapiService,
    private projectService: ProjectApiService,
    private bidService: BidApiService
  ) {
    if (localStorage.getItem('userId') != null) {
      this.freelancerId = Number(localStorage.getItem('userId'));
    }
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projectId = +params['projectId'];
    });
    this.getProjectById(this.projectId);
    if (this.freelancerId != 0) {
      this.getFreelancerBid(this.freelancerId);
    }
  }

  async getProjectById(projectId: number) {
    this.data = await this.projectService.getProjects({ projectId: projectId });
    this.bidEndDate = this.data && this.data[0]?.bidEndDate ? new Date(this.data[0].bidEndDate) : null;
    this.bidStartDate = this.data && this.data[0]?.bidStartDate ? new Date(this.data[0].bidStartDate) : null;
    const diffTimestamp = (this.bidEndDate?.getTime() ?? 0) - (this.bidStartDate?.getTime() ?? 0);
    this.projectDaysLeft = Math.ceil(diffTimestamp / (1000 * 60 * 60 * 24));
    this.getClientLocation(this.data && this.data[0]?.clientId || 0)
  }
  async getClientLocation(clientId: number) {
    this.locationData = await this.userService.getAllUsers({ userId: clientId });
  }

  async getFreelancerBid(freelancerId: number) {
    const bidData = await this.bidService.getBids({ freelancerId: freelancerId, projectId: this.projectId });
    if (bidData != null && bidData.length > 0) {
      const bid = bidData[0];

      this.insertBid.patchValue({
        amount: bid.amount.toString(),
        deliveryDays: bid.deliveryDays.toString(),
        description: bid.description
      });
      if (bid.status === BidStatus.REJECTED.toString()) {
        this.isRejected = true
      }

      console.log(bidData);
    }
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

  countAvgRating(ratings: any): number {
    if (ratings.length > 0) {
      let ratingTotal: number = 0;
      for (let i = 0; i < ratings.length; i++) {
        ratingTotal += ratings[i].rating;
      }
      const averageBid = ratingTotal / ratings.length;
      return parseFloat(averageBid.toFixed(2));
    } else {
      return 0;
    }
  }
}
