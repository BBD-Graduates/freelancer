import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserapiService } from '../../service/user-api.service';
import { ProjectApiService } from '../../service/project-api.service';
import { HttpClient } from '@angular/common/http';
import { config } from 'src/app/config';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { ProjectResponse } from 'src/app/shared/model/projectResponse';

@Component({
  selector: 'fl-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css'],
})
export class LandingPageComponent {
  canShowVideo = false;
  currentSize = 'S';

  constructor(
    private projectApiService: ProjectApiService,
    private router: Router,
    private http: HttpClient
  ) {}

  projectData: any;
  projects: any;
  projectLength: number = 10;
  async ngOnInit(): Promise<void> {
    this.projectData = await this.http
      .get(config.projectApi.getAllStatusProjects)
      .toPromise();

    this.projects = this.projectData as ApiResponse;
    this.projectLength = this.projects.response.length;
  }
}
