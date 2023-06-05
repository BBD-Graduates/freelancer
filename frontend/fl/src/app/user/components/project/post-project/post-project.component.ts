import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SkillApiService } from 'src/app/user/service/skill-api.service';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import { ProjectModel } from 'src/app/shared/model/projectModel';

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
    projectName: new FormControl('', [Validators.required]),
    projectDescription: new FormControl('', [Validators.required]),
    isConfidential: new FormControl('', [Validators.required]),
    bidStartDate: new FormControl('', [Validators.required]),
    bidEndDate: new FormControl('', [Validators.required]),
    minPrice: new FormControl('', [Validators.required]),
    maxPrice: new FormControl('', [Validators.required]),
    status: new FormControl('', [Validators.required])
  });
  skillList: any;
  errorMessage: string;

  constructor(private _httpClient: HttpClient, private route: ActivatedRoute, private skillAPi: SkillApiService,private projectApi:ProjectApiService) {
    this.loadSkills();
    this.errorMessage='';

  }

  collectProject() {
    if (this.selectedOptions.length<=5){

      const projectData: ProjectModel = {
        projectName: this.insertProject.value.projectName ?? '',
        projectDescription: this.insertProject.value.projectDescription ?? '',
        isConfidential: this.insertProject.value.isConfidential === 'true',
        bidStartDate: new Date(this.insertProject.value.bidStartDate ?? ''),
        bidEndDate: new Date(this.insertProject.value.bidEndDate ?? ''),
        minPrice: Number(this.insertProject.value.minPrice ?? ''),
        maxPrice: Number (this.insertProject.value.maxPrice ?? ''),
        status: this.insertProject.value.status ?? '',

      };

      this.projectApi.postProject(projectData)
        .subscribe(
          (response) => {
            console.log(response);
            this.alert = true;
            this.insertProject.reset();
          },
          (error) => {
            console.error('Error calling postProject service:', error);
          }
        );
    }else{
      this.errorMessage = "You can select a maximum of 5 options.";
    }

  }
  closeAlert() {
    this.errorMessage = '';
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
