import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
    this.userId = Number(localStorage.getItem('userId'));
  }

  projects: any;

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectApiService.getProjects({
      clientId: this.userId,
      status: this.selectedStatus,
    });
  }

  countAvgBid(bid: any): number {
    if (bid.length > 0) {
      let bidTotal: number = 0;
      for (let i = 0; i < bid.length; i++) {
        bidTotal += bid[i].amount;
      }
      return bidTotal / bid.length;
    } else {
      return 0;
    }
  }
}
