import { Component, Input } from '@angular/core';
import { ProjectApiService } from 'src/app/user/service/project-api.service';
import { ProjectResponse } from '../model/projectResponse';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'fl-shared-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent {
  @Input('projectId') projectId = 0;
  
  data : ProjectResponse[] |null=null;
  constructor(private projectService:ProjectApiService,private route: ActivatedRoute){
    this.route.params.subscribe(params => {
      this.projectId = params['projectId'];
    });
    this.getProjectData(this.projectId);

  }
  async getProjectData(projectId:number){
    this.data = await this.projectService.getProjects({projectId: this.projectId});
  }
  
}
