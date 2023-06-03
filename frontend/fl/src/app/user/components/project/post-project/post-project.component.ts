import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { config } from 'src/app/config';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SkillApiService } from 'src/app/user/service/skill-api.service';

@Component({
  selector: 'fl-post-project',
  templateUrl: './post-project.component.html',
  styleUrls: ['./post-project.component.css']
})
export class PostProjectComponent {


  projectdata: any = [];
  alert: boolean = false
  skillName: any = [];
  insertProject = new FormGroup({
    projectName: new FormControl('', Validators.required),
    projectDescription: new FormControl('', Validators.required),
    isConfidential: new FormControl('', Validators.required),
    bidStartDate: new FormControl('', Validators.required),
    bidEndDate: new FormControl('', Validators.required),
    minPrice: new FormControl('', Validators.required),
    maxPrice: new FormControl('', Validators.required),
    status: new FormControl('', Validators.required)

  })
  skillList: any;
  constructor(private _httpClient: HttpClient, private route: ActivatedRoute, private skillAPi: SkillApiService) { 
    this.loadSkills();

  }
  
  saveProject(projectdata: any) {
    return this._httpClient.post(config.projectApi.insertProject, projectdata);
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

  options:any = [];
  selectedOptions: string[] = [];
  isDropdownOpen = false;
  async loadSkills() {
    const skillList = await this.skillAPi.getSkill();
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
}
