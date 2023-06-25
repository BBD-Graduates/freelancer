import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-past-projects',
  templateUrl: './past-projects.component.html',
  styleUrls: ['./past-projects.component.css'],
})
export class PastProjectsComponent {
  userId: number = 0;
  selectedStatus: String[] = [ProjectStatus.COMPLETED];

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
  p:number=1
  projects: any;

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectApiService.getProjects({
      clientId: this.userId,
      status: this.selectedStatus,
    });
  }
  rating(projectid:number){
    
  }
}
