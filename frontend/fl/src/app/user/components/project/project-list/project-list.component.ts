import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { elementAt } from 'rxjs';

@Component({
  selector: 'fl-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
})
export class ProjectListComponent implements OnInit {
  data: any = [];
  alert: boolean = false;
  skillName: String = '';

  insertBid = new FormGroup({
    projectId: new FormControl(this.route.snapshot.paramMap.get('id')),
    freelancerId: new FormControl('', Validators.required),
    amount: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    deliveryDays: new FormControl('', Validators.required),
  });
  bidStartdate = '';
  currentDate = '';
  locationData: any = [];
  http: any;
  constructor(
    private _httpClient: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Trick the Router into believing it's last link wasn't previously loaded
        this.router.navigated = false;
      }
    });
  }
  ngOnInit(): void {
    let curDate = new Date().toISOString().slice(0, 10).toString();
    if (this.route.snapshot.paramMap.get('id') != null) {
      const id = this.route.snapshot.paramMap.get('id');
      this._httpClient
        .get(config.projectApi.getProjectBySkillId + id)
        .subscribe((response: any) => {
          this.data = response;
          this._httpClient
            .get(config.skillApi.getSkillBySkillId + id)
            .subscribe((data: any) => {
              this.skillName = data.response[0].skillName;
            });
        });
    } else {
      const id = this.route.snapshot.paramMap.get('categoryid');
      this._httpClient
        .get(config.projectApi.getProjectByCategoryId + id)
        .subscribe((response: any) => {
          this.data = response;
        });
      this._httpClient
        .get(config.skillApi.getCategoryByCategoryId + id)
        .subscribe((data: any) => {
          this.skillName = data.response[0].categoryName;
        });
    }
    console.log('data', this.data);

    this.bidStartdate = this.data.bidStartdate;
    console.log('date', this.showDate(this.bidStartdate));

    const pid = this.route.snapshot.paramMap.get('id');
    this._httpClient
      .get(config.UserApi.getLocation)
      .subscribe((response: any) => {
        this.locationData = response;
      });
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

  countAvgBid(bid: any): number {
    if (bid.length > 0) {
      let bidTotal: number = 0;
      for (let i = 0; i < bid.length; i++) {
        bidTotal += bid[i].amount;
      }
      return bidTotal / bid.length;
    } else {
      return 0;
    }
  }

  options = ['Option 1', 'Option 2', 'Option 3', 'Option 4'];
  selectedOptions: string[] = [];
  isDropdownOpen = false;

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  closeDropdown() {
    this.isDropdownOpen = false;
  }

  toggleOption(option: string) {
    const index = this.selectedOptions.indexOf(option);
    if (index > -1) {
      this.selectedOptions.splice(index, 1);
    } else {
      this.selectedOptions.push(option);
    }
  }

  isSelected(option: string) {
    return this.selectedOptions.includes(option);
  }

  showDate(dateTimeString: any) {
    const date = new Date(dateTimeString);
    const formattedDate = date.toLocaleString('en-US', {
      month: 'long',
      day: 'numeric',
      year: 'numeric',
    });

    return formattedDate;
  }
}
