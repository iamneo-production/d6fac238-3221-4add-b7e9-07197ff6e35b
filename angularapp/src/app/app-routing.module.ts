import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminhomepageComponent } from './adminhomepage/adminhomepage.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { RoleGuard } from './auth/role.guard';
import { SignupComponent } from './auth/signup/signup.component';
import { HomepageComponent } from './homepage/homepage.component';

const routes: Routes = [
  {path:'' ,component:LoginComponent},
  {path:'signup' ,component:SignupComponent},
  {path:'home' ,component:HomepageComponent,canActivate:[AuthGuard]},
  {path:'admin' ,component:AdminhomepageComponent,canActivate:[AuthGuard,RoleGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
