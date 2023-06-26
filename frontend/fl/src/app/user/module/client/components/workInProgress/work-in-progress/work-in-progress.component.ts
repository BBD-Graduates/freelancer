import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { ProjectStatus } from 'src/app/enums/projectStatusEnums';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'fl-work-in-progress',
  templateUrl: './work-in-progress.component.html',
  styleUrls: ['./work-in-progress.component.css'],
})
export class WorkInProgressComponent {
  p:number=1;
  userId: number = 0;

  selectedStatus: String[] = [ProjectStatus.IN_PROGRESS];

  constructor(
    private projectApiService: ProjectApiService,
    private route: ActivatedRoute,
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
      clientId: this.userId,
      status: this.selectedStatus,
    });
  }
  async completeProject(projectId:number) {
    Swal.fire({
      title: 'Complete this project?',
      text: 'This project will be marked as completed.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#14b8a6',
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then(async (result) => {
      if (result.value) {
        const response = await this.projectApiService.updateProject({ projectId: projectId, projectStatus: ProjectStatus.COMPLETED.toString() });
        if (response != null) {
          Swal.fire({
            title: 'Marked Completed',
            text: 'You can see this project in "Past Projects" section',
            icon: 'success',
            confirmButtonColor: '#14b8a6',
            confirmButtonText: 'Ok',
          }).then(() => {
            this.router.navigateByUrl('/home/myProjects/client/past');
          });
        }
      }
    });
  }
}
