import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { config } from 'src/app/config';
import { SkillApiService } from '../../service/skill-api.service';

@Component({
  selector: 'fl-job-links',
  templateUrl: './job-links.component.html',
  styleUrls: ['./job-links.component.css'],
})
export class JobLinksComponent {
  menuData: any;
  constructor(private skillService: SkillApiService) {}

  sessionStatus = localStorage.getItem('userEmail') == null;

  ngOnInit(): void {
    this.getcategory();
  }
  ngOnChange(): void {
    this.sessionStatus = localStorage.getItem('userEmail') == null;
  }
  async getcategory(){
    this.menuData = await this.skillService.getCategory({});
  }
}
