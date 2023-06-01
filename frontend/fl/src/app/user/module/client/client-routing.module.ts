import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { OpenProjectsComponent } from './components/open-projects/open-projects.component';
import { PastProjectsComponent } from './components/past-projects/past-projects.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    children:[
      {
        path:'',
        component:OpenProjectsComponent,
      },
      {
        path:'past',
        component:PastProjectsComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
