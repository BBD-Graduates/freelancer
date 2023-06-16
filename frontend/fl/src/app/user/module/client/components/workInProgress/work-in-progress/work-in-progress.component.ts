import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-work-in-progress',
  templateUrl: './work-in-progress.component.html',
  styleUrls: ['./work-in-progress.component.css'],
})
export class WorkInProgressComponent {
  userId: number = 0;
  selectedStatus: String[] = [ProjectStatus.IN_PROGRESS];

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
