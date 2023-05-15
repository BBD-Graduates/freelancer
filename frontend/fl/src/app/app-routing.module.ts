import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { TestComponent } from './shared/test/test.component';
import { ProjectListComponent } from './user/project/project-list/project-list.component';
import { ProjectDetailsComponent } from './user/project/project-details/project-details.component';

const routes: Routes = [

  {path:'', component:HomeComponent},
  {path:'test', component:TestComponent},
  {path:'test/:id', component:TestComponent},
  {path:'skill', component:ProjectListComponent},
  {path:'skill/:id', component:ProjectListComponent},
  {path:'project-details', component:ProjectDetailsComponent},
  {path:'skill/9/project-details', component:ProjectListComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
