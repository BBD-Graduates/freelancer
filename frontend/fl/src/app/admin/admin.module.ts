import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { SharedModule } from '../shared/shared.module';
import { AdminComponent } from './admin/admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CategoryComponent } from './components/category/category.component';
import { SkillComponent } from './components/skill/skill.component';
import { ProjectListComponent } from './components/project/project-list/project-list.component';
import { ProjectDetailComponent } from './components/project/project-detail/project-detail.component';


@NgModule({
  declarations: [
    AdminComponent,
    DashboardComponent,
    CategoryComponent,
    SkillComponent,
    ProjectListComponent,
    ProjectDetailComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
  ],
  exports:[
    AdminComponent
  ]
})
export class AdminModule { }
