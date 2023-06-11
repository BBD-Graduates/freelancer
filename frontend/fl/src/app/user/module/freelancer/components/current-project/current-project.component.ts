import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-current-project',
  templateUrl: './current-project.component.html',
  styleUrls: ['./current-project.component.css'],
})
export class CurrentProjectComponent {
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
      freelancerId: this.userId,
      status: this.selectedStatus,
    });
  }
}
