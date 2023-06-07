import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import { SkillApiService } from 'src/app/user/service/skill-api.service';

@Component({
  selector: 'fl-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],
})
export class ProjectListComponent implements OnInit {
  constructor(
    private _httpClient: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private projectApiService: ProjectApiService,
    private skillApi:SkillApiService

  ) {
    this.loadSkills();
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
  }
  data: any = [];
  alert: boolean = false;
  skillName: String = '';
  projects:any;
  categoryId!:number;
  skillId!:number;

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
  
  async ngOnInit(): Promise<void> {
  
    let curDate = new Date().toISOString().slice(0, 10).toString();

    if (this.route.snapshot.paramMap.get('skillid') != null) {
      this.skillId =parseInt(this.route.snapshot.paramMap.get('skillid')||'');
      this.data= await this.projectApiService.getProjects({skillId :this.skillId})
      console.log('skilldata', this.data);
          this._httpClient
            .get(config.skillApi.getSkillBySkillId + this.skillId)
            .subscribe((data: any) => {
              this.skillName = data.response[0].skillName;
            });   
    } 
    else if (this.route.snapshot.paramMap.get('categoryid') != null){
      this.categoryId = parseInt(this.route.snapshot.paramMap.get('categoryid')|| '');
      this.data=await this.projectApiService.getProjects({categoryId :this.categoryId})
      console.log('catdata', this.data);
      this._httpClient 
        .get(config.skillApi.getCategoryByCategoryId + this.categoryId)
        .subscribe((data: any) => {
          this.skillName = data.response[0].categoryName;
          
        });
    } else{
      this.data= await this.projectApiService.getProjects({});
      console.log('Alldata', this.data);
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
      const averageBid = bidTotal / bid.length;
      return parseFloat(averageBid.toFixed(2));
    } else {
      return 0;
    }
  }

  options:any = [];
  selectedOptions: string[] = [];
  isDropdownOpen = false;
  async loadSkills() {
    const skillList = await this.skillApi.getSkill();
    skillList?.forEach(skills=>{
      this.options.push(skills.skillName);
    })
    console.log(this.options,'skillnames')
  }

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


  // options = ['Option 1', 'Option 2', 'Option 3', 'Option 4','Option 1', 'Option 2', 'Option 3', 'Option 4'];
  // selectedOptions: string[] = [];
  // isDropdownOpen = true;

  // toggleDropdown() {
  //   this.isDropdownOpen = !this.isDropdownOpen;
  // }

  // closeDropdown() {
  //   this.isDropdownOpen = false;
  // }

  // toggleOption(option: string) {
  //   const index = this.selectedOptions.indexOf(option);
  //   if (index > -1) {
  //     this.selectedOptions.splice(index, 1);
  //   } else {
  //     this.selectedOptions.push(option);
  //   }
  // }

  // isSelected(option: string) {
  //   return this.selectedOptions.includes(option);
  // }


