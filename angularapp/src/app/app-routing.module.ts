import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminhomepageComponent } from './adminhomepage/adminhomepage.component';
import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { RoleGuard } from './auth/role.guard';
import { SignupComponent } from './auth/signup/signup.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { HomepageComponent } from './homepage/homepage.component';
import { OrderComponent } from './order/order.component';
import { ViewComponent } from './view/view.component';

const routes: Routes = [
  {path:'' ,component:LoginComponent},
  {path:'signup' ,component:SignupComponent},
  {path:'home' ,component:HomepageComponent,canActivate:[AuthGuard]},
  {path:'admin' ,component:AdminhomepageComponent,canActivate:[AuthGuard,RoleGuard]},
  {path:'cart',component:CartComponent,canActivate:[AuthGuard]},
  {path:'myorders',component:OrderComponent,canActivate:[AuthGuard]},
  {path:'checkout',component:CheckoutComponent,canActivate:[AuthGuard]},
  {path:'vieworder/:id',component:ViewComponent,canActivate:[AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
