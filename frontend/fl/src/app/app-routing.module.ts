import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './user/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';


const routes: Routes = [
  // { path: 'user', loadChildren:() => import('./user/user.module').then(m=>m.UserModule) },
  // { path: 'admin', loadChildren:() => import('./admin/admin.module').then(m=> m.AdminModule) },
  // { path: '', redirectTo:'/user/home',pathMatch:'full' },
  {path: 'home/', component: HomeComponent  },
  {path: '', component: HomeComponent  },
  {path: 'admin', component: AdminComponent  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
