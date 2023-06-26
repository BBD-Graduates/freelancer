import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { skillResponse } from 'src/app/shared/model/skillResponse';
import { SkillApiService } from 'src/app/user/service/skill-api.service';
import { UserapiService } from 'src/app/user/service/user-api.service';

@Component({
  selector: 'fl-browse-freelancers',
  templateUrl: './browse-freelancers.component.html',
  styleUrls: ['./browse-freelancers.component.css'],
})
export class BrowseFreelancersComponent implements OnInit{
  constructor(private userApiService: UserapiService, private router: Router,private skillApi:SkillApiService) {
    this.loadSkills();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
  }
  p:number=1;
  userDetailsList: any[] = [];
  userData: any;
  locationData:any[]=[];
  selectedSkills: number[] = [];
  searchValue: string = '';

  userDetails:
    | {
        userId: number;
        profileUrl: any;
        firstName: any;
        lastName: any;
        headLine: any;
        summary: any;
        company: any;
        email: any;
        phNo: any;
        createdDate: any;
        state: any;
        country: any;
        ratings: any;
        skills: any;
        languages: any;
      }
    | undefined;



  async ngOnInit(): Promise<void> {
    this.userData = await this.userApiService.getAllUsers({});
    const userRes = this.userData?.response;

    userRes.forEach((userData: any) => {
      const response = userData || {};
      this.userDetails = {
        userId: response.userId || '',
        profileUrl: response['photoUrl']?.replace('s96-c', 's400-c') || '',
        firstName: response['firstName']?.toUpperCase() || '',
        lastName: response['lastName']?.toUpperCase() || '',
        headLine: response['headLine'] || '',
        summary: response['summary'] || '',
        company: response['company'] || '',
        email: response['email'] || '',
        phNo: response['phNo'] || '',
        createdDate: response['createdDate'] || '',
        state: response['stateName'] || '',
        country: response['countryName'] || '',
        ratings: response['ratings'] || [],
        skills: response['skills'] || [],
        languages: response['languages'] || [],
      };
      if(response.userId!= localStorage.getItem('userId')){
        this.userDetailsList.push(this.userDetails);
      }
    });
  }

  async getUsersBySkills()
  {

    if(this.selectedSkills.length>0){
      this.userDetailsList = this.userDetailsList.filter((userData:any) => {
        const userSkills = userData.skills.map((skill:any) => skill.skillId);
        return this.selectedSkills.some(skill => userSkills.includes(skill));
      });
    }else{
      this.ngOnInit();
    }
  }

  options:any = [];
  selectedOptions: string[] = [];
  isDropdownOpen = false;
  skillOptions: skillResponse[] | null = null;

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
}
