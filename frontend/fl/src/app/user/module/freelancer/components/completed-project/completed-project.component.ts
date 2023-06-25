import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-completed-project',
  templateUrl: './completed-project.component.html',
  styleUrls: ['./completed-project.component.css'],
})
export class CompletedProjectComponent {
  userId: number = 0;
  p:number=1;
  selectedStatus: String[] = [ProjectStatus.COMPLETED];

  constructor(
    private projectApiService: ProjectApiService,
    private router: Router
  ) {
    this.userId = Number(localStorage.getItem('userId'));
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
  }

  projects: any;

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectApiService.getProjects({
      freelancerId: this.userId,
      status: this.selectedStatus,
    });
  }
}
