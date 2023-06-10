import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css'],
})
export class ProjectDetailComponent {
  @Input('projectId') projectId = 0;

  constructor(
    private projectService: ProjectApiService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe((params) => {
      this.projectId = params['projectId'];
    });
    console.log(this.projectId);
  }
}
