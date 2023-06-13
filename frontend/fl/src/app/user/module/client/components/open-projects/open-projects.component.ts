import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-open-projects',
  templateUrl: './open-projects.component.html',
  styleUrls: ['./open-projects.component.css'],
})
export class OpenProjectsComponent {
  userId: number = 0;
  selectedStatus: String[] = [
    ProjectStatus.POSTED,
    ProjectStatus.BID_IN_PROGRESS,
    ProjectStatus.BID_COMPLETE,
  ];

  constructor(
    private projectApiService: ProjectApiService,
    private router: Router
  ) {
    this.userId = Number(localStorage.getItem('userId'));
  }

  projects: any;

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectApiService.getProjects({
      clientId: this.userId,
      status: this.selectedStatus,
    });
  }
}
