import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  ) { }
  updatedUserProfile = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    headLine: new FormControl('', Validators.required),
    summary: new FormControl('', Validators.required),
    company: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    phNo: new FormControl('', Validators.required),
    createdDate: new FormControl('', Validators.required),
    state: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    joiningDate: new FormControl('', Validators.required),
    dateString: new FormControl('', Validators.required),
    photoUrl: new FormControl('', Validators.required),
  });
  locationDetails=new FormGroup({
    pincode:new FormGroup('',Validators.required),
    address1:new FormGroup('',Validators.required),
    address2:new FormGroup('',Validators.required),
  })
  userId: number | null = 0;
  ngOnInit() {
    this.getUserDetails();
    this.userId = parseInt(localStorage.getItem('userId') || '0');
  }

  ratings: any[] = [];
  skills: any[] = [];
  languages: any[] = [];

  sessionStatus = localStorage.getItem('userId') == null;

  async getUserDetails() {
    let userId = parseInt(localStorage.getItem('userId') ?? '');
    const user = await this.userapiService.getAllUsers({
      userId: userId,
    });
    this.updatedUserProfile.patchValue({
      firstName: user?.response[0]['firstName'].toUpperCase(),
      lastName : user?.response[0]['lastName'].toUpperCase(),
      headLine : user?.response[0]['headLine'],
      summary : user?.response[0]['summary'],
      company : user?.response[0]['company'],
      email : user?.response[0]['email'],
      phNo  : user?.response[0]['phNo'],
      createdDate : user?.response[0]['createdDate'],
      state : user?.response[0]['stateName'],
      country : user?.response[0]['countryName'],
    })
    this.locationDetails.patchValue({
      pincode:user?.response[0]['countryName'],
    })

    // this.profileUrl = user?.response[0]['photoUrl'];
    // this.firstName = user?.response[0]['firstName'].toUpperCase();
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
