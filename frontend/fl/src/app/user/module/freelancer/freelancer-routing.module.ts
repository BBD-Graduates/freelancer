import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BidComponent } from './components/bid/bid.component';
import { CurrentProjectComponent } from './components/current-project/current-project.component';
import { CompletedProjectComponent } from './components/completed-project/completed-project.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: BidComponent,
      },
      {
        path: 'current-project',
        component: CurrentProjectComponent,
      },
      {
        path: 'completed',
        component: CompletedProjectComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FreelancerRoutingModule {}
