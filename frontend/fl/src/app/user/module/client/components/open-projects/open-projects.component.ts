import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
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
  p:number=1;
  constructor(
    private projectApiService: ProjectApiService,
    private router: Router
  ) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
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
