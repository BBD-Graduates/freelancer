import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './user/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';

const routes: Routes = [
  {path: 'home/', component: HomeComponent,pathMatch:'full'  },
  {path: '', redirectTo:'home',pathMatch:'full'  },
  {path: 'admin', component: AdminComponent  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
  getLogin() {}
}
