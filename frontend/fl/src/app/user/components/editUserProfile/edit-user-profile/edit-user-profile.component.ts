import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { skillResponse } from 'src/app/shared/model/skillResponse';
import { SkillApiService } from 'src/app/user/service/skill-api.service';
import { UserapiService } from 'src/app/user/service/user-api.service';

@Component({
  selector: 'fl-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css'],
})
export class EditUserProfileComponent {
  constructor(
    private userapiService: UserapiService,
    private router: Router,
    private skillAPi: SkillApiService
  ) {}
  selectcountry!: FormControl;
  selectstate!: FormControl;
  selectcity!: FormControl;
  ngOnInit() {
    this.getUserDetails();

    this.selectcountry = new FormControl('');
    this.selectstate = new FormControl('');
    this.selectcity = new FormControl('');
  }

  profileUrl!: string;
  firstName!: string;
  lastName!: string;
  headLine!: string;
  summary!: string;
  company!: string;
  email!: string;
  phNo!: number;
  createdDate!: Date;
  state!: string;
  country!: string;
  joiningDate!: Date;
  dateString!: string;
  photoUrl!: string;

  ratings: any[] = [];
  skills: any[] = [];
  languages: any[] = [];

  sessionStatus = localStorage.getItem('userId') == null;

  async getUserDetails() {
    let userId = parseInt(localStorage.getItem('userId') ?? '');
    const user = await this.userapiService.getAllUsers({
      userId: userId,
    });

    this.profileUrl = user?.response[0]['photoUrl'];
    this.firstName = user?.response[0]['firstName'].toUpperCase();
    this.lastName = user?.response[0]['lastName'].toUpperCase();
    this.headLine = user?.response[0]['headLine'];
    this.summary = user?.response[0]['summary'];
    this.company = user?.response[0]['company'];
    this.email = user?.response[0]['email'];
    this.phNo = user?.response[0]['phNo'];
    this.createdDate = user?.response[0]['createdDate'];
    this.state = user?.response[0]['stateName'];
    this.country = user?.response[0]['countryName'];
    this.photoUrl = this.profileUrl.replace('s96-c', 's400-c');
    this.ratings = user?.response[0]['ratings'];
    this.skills = user?.response[0]['skills'];
    this.languages = user?.response[0]['languages'];
  }
  isDropdownOpen = false;
  skillOptions: skillResponse[] | null = null;
  selectedOptions: string[] = [];
  selectedSkills: number[] = [];

  async loadSkills() {
    this.skillOptions = await this.skillAPi.getSkill();
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
    console.log(this.selectedSkills);
  }

  isSelected(skillId: number) {
    return this.selectedSkills.includes(skillId);
  }
}
