import { Component } from '@angular/core';

@Component({
  selector: 'fl-my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.css'],
})
export class MyProjectsComponent {
  IsClient = false;
  IsFreelancer = true;

  changeOption(){
    this.IsClient = !this.IsClient;
    this.IsFreelancer = !this.IsFreelancer;
  }

}
