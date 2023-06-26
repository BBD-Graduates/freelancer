import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import { SkillApiService } from 'src/app/user/service/skill-api.service';
import { async } from 'rxjs';
import { skillResponse } from 'src/app/shared/model/skillResponse';
import { ProjectModel } from 'src/app/shared/model/projectModel';

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
    private skillApi: SkillApiService
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
  p: number = 1;
  data: any = [];
  alert: boolean = false;
  skillName: String = '';
  projects: any;
  categoryId!: number;
  skillIds!: number[];
  filteredSkillId!: number;
  selectedSkills: number[] = [];

  bidStartdate = '';
  currentDate = '';
  locationData: any = [];
  http: any;
  searchValue: string = '';
  projectData: ProjectModel[]=[];
  async ngOnInit(): Promise<void> {
    let curDate = new Date().toISOString().slice(0, 10).toString();

    if (this.route.snapshot.paramMap.get('skillId') != null) {
      this.skillIds.push(
        parseInt(this.route.snapshot.paramMap.get('skillId') || '')
      );
      this.filteredSkillId = parseInt(
        this.route.snapshot.paramMap.get('skillId') || ''
      );
      this.data = await this.projectApiService.getProjects({
        skillIds: this.skillIds,
      });
      console.log('skilldata', this.data);
      this._httpClient
        .get(config.skillApi.getSkillBySkillId + this.filteredSkillId)
        .subscribe((data: any) => {
          this.skillName = data.response[0].skillName;
        });
    } else if (this.route.snapshot.paramMap.get('categoryid') != null) {
      this.categoryId = parseInt(
        this.route.snapshot.paramMap.get('categoryid') || ''
      );
      this.data = await this.projectApiService.getProjects({
        categoryId: this.categoryId,
      });
      console.log('catdata', this.data);
      this._httpClient
        .get(config.skillApi.getCategoryByCategoryId + this.categoryId)
        .subscribe((data: any) => {
          this.skillName = data.response[0].categoryName;
        });
    } else {
      this.data = await this.projectApiService.getProjects({});
      // console.log('Alldata', this.data);
    }

    this.bidStartdate = this.data.bidStartdate;

    const pid = this.route.snapshot.paramMap.get('id');
    this._httpClient
      .get(config.UserApi.getLocation)
      .subscribe((response: any) => {
        this.locationData = response;
      });
  }


  async getProjectsBySkills() {

    if(this.selectedSkills.length>0){
      this.data = this.data.filter((project:any) => {
        const projectSkills = project.skills.map((skill:any) => skill.skillId);
        return this.selectedSkills.some(skill => projectSkills.includes(skill));
      });
    }else{
      this.data=await this.projectApiService.getProjects({});
    }


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
  skillOptions: skillResponse[] | null = null;
  options: any = [];
  selectedOptions: string[] = [];
  isDropdownOpen = false;

  async loadSkills() {
    this.skillOptions = await this.skillApi.getSkill();
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  closeDropdown() {
    this.isDropdownOpen = false;
  }

  toggleOption(option: string, skillId: number) {
    const index = this.selectedOptions.indexOf(option);
    if (index > -1) {
      this.selectedOptions.splice(index, 1);
      this.selectedSkills.splice(index, 1);
    } else {
      this.selectedOptions.push(option);
      this.selectedSkills.push(skillId);
    }
  }

  isSelected(skillId: number) {
    return this.selectedSkills.includes(skillId);
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
