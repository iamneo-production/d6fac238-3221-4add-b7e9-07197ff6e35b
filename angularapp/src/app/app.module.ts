import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminhomepageComponent } from './adminhomepage/adminhomepage.component';
import { FooterComponent } from './footer/footer.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CheckoutComponent } from './checkout/checkout.component';
import { ViewComponent } from './view/view.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LoginComponent,
    SignupComponent,
    HomepageComponent,
<<<<<<< HEAD
    NavbarComponent,
    AdminhomepageComponent,
    FooterComponent,
    CartComponent,
    OrderComponent,
    CheckoutComponent,
    ViewComponent
=======
    AdminhomepageComponent,
    FooterComponent,
    ProductDetailsComponent
>>>>>>> e1d86f65af87f2126f3ed0db6cc6f2f70e345eca
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
<<<<<<< HEAD
    NgbModule,
=======
>>>>>>> e1d86f65af87f2126f3ed0db6cc6f2f70e345eca
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
